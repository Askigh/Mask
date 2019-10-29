package net.akami.atosym.merge;

import net.akami.atosym.merge.property.SimpleElementMergeProperty;
import net.akami.atosym.merge.property.OverallMergeProperty;
import net.akami.atosym.merge.property.OverallSequencedMergeProperty;

import java.util.*;

/**
 * A merge concerning sequences of elements. <br>
 * The SimpleSequencedMerge behavior is a slightly more complex merge than usual merges. As every normal merge, it first
 * tries to compute a full list from the {@link OverallSequencedMergeProperty}s handled. However, if no property matches
 * the two lists (which is likely to happen), both lists will be browsed to try to merge elements. <br>
 * Each element of the first list will be compared to each element of the second list. If an {@link SimpleElementMergeProperty}
 * is found to be suitable for the two elements, one or several results will be added to a new list which will be the merge result.
 * Elements which couldn't be merged at all (elements to which no property applied) will eventually be added to the list. <br>
 * See {@link #merge(List, List, boolean)} or {@link #merge(List, List, boolean, int)} for further information.
 *
 * @param <T> the type of elements composing the sequences
 * @author Antoine Tran
 */
public interface SimpleSequencedMerge<T> extends SequencedMerge<T, List<T>, SimpleElementMergeProperty<T>> {

    /**
     * Generates a list containing the element properties. Contrary to {@link OverallMergeProperty}s, they don't directly
     * apply to the input given (being the two sequences of elements), but to elements forming the lists. <br>
     * Every time two elements are compared, a new list of properties must be generated. The reason for that is that these properties
     * might handle different fields that depend on the constructor parameters (the two elements). Using setters instead of
     * re-instantiating new properties is also not recommended, since a property might recursively use itself, and change
     * the value of the elements before the end of the calculation of the result. If you are guaranteed that recursive usage will not
     * happen, you might consider adding setters to your properties instead and always return the same list.
     * @param p1 the element from the first list to compare
     * @param p2 the element from the second list to compare
     * @return a list of properties suiting {@code p1} and {@code p2}
     */
    List<SimpleElementMergeProperty<T>> generateElementProperties(T p1, T p2);

    /**
     * Method that should usually not be used outside this class. Java 8 does not support private methods in interfaces. <br>
     * @param p1 the element from the first list to compare
     * @param p2 the element from the second list to compare
     * @return an optional containing the first suitable property for the two elements. If no property matches the two elements,
     * an empty optional is returned
     */
    default Optional<SimpleElementMergeProperty<T>> prepare(T p1, T p2) {
        for(SimpleElementMergeProperty<T> property : generateElementProperties(p1, p2)) {
            if(property.prepare()) return Optional.of(property);
        }
        return Optional.empty();
    }

    /**
     * Constructs a list from the subtypes of the handled type. However, you can not expect to retrieve a list
     * containing only objects having the same type as the parameter types. Therefore, a {@code SequenceMerge<Object>}
     * for instance will always return a list of objects, since every item added into the constructed list is not
     * guaranteed to be anything else than an Object. <br>
     * See {@link #merge(List, List, boolean)} for further information
     * @param l1 the first list to merge
     * @param l2 the second list to merge
     * @param selfMerge whether the merge is proceeded with a single list or not
     * @param <S> the subtype
     * @return a merge of the two lists
     */
    default <S extends T> List<T> subtypeMerge(List<S> l1, List<S> l2, boolean selfMerge) {
        return subtypeMerge(l1, l2, selfMerge);
    }

    /**
     * Merges the two lists. <br>
     * Basically, compare each element of the first list with each element of the second one. If a result can be computed
     * out of these two elements, they will be removed and the computed result will be added into the constructed list. <br>
     * If the same list is passed in both list parameters, elements with the same index won't be compared.
     * Because the elements are removed as you go, an element that found another compatible element won't be merged any more. <br>
     * After all comparisons, the elements which could not be merged will be added into the constructed list. <br>
     *
     * Note that the method should most of the time not be overridden through subclasses, since the behavior corresponds to list merging
     * @param l1 the first list to merge
     * @param l2 the second list to merge
     * @param selfMerge whether the merge is proceeded with a single list or not
     * @return a merge of the two lists
     */
    @Override
    default List<T> merge(List<T> l1, List<T> l2, boolean selfMerge) {
        // Meaningless override of method for now
        return SequencedMerge.super.merge(l1, l2, selfMerge);
    }

    List<T> getTargetList();

    @Override
    default MergeFlowModification<T> associate(SimpleElementMergeProperty<T> property) {
        property.blendResult(this.getTargetList());
        return SequencedMerge.super::nullifyElements;
    }

    @Override
    default List<T> loadFinalResult() {
        return getTargetList();
    }

    @Override
    default List<T> andThenMerge() {
        return merge(getTargetList(), getTargetList(), true);
    }

    /**
     * Merges the two lists. <br>
     * Basically, compare each element of the first list with each element of the second one. If a result can be computed
     * out of these two elements, they will be removed and the computed result will be added into the constructed list. <br>
     * If the same list is passed in both list parameters, elements with the same index won't be compared.
     * Because the elements are removed as you go, an element that found another compatible element won't be merged any more. <br>
     * After all comparisons, the elements which could not be merged will be added into the constructed list. <br>
     *
     * Note that the method should most of the time not be overridden through subclasses, since the behavior corresponds to list merging
     * @param l1 the first list to merge
     * @param l2 the second list to merge
     * @param selfMerge whether the merge is proceeded with a single list or not
     * @param initialCapacity the initial capacity of the new list, 0 if not specified
     * @return a merge of the two lists
     */
    /*default List<T> merge(List<T> l1, List<T> l2, boolean selfMerge, int initialCapacity) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        boolean requestsStartingOver = false;
        List<T> finalResult = new ArrayList<>(initialCapacity);
        Optional<List<T>> potentialResult = resultFromProperties(l1, l2);
        if(potentialResult.isPresent()) return potentialResult.get();

        int i = 0;
        for (T element : l1) {
            if (element == null) {
                i++;
                continue;
            }
            int j = 0;
            for (T element2 : l2) {

                if (selfMerge && i == j) { j++; continue; }
                if (element2 == null) { j++; continue; }

                Optional<SimpleElementMergeProperty<T>> optionalMergeData = prepare(element, element2);
                if (optionalMergeData.isPresent()) {
                    SimpleElementMergeProperty<T> mergeData = optionalMergeData.get();

                    if(selfMerge) requestsStartingOver = true;

                    nullifyElements(l1, l2, i, j, finalResult, mergeData);

                    if(mergeData.isRestartRequired()) {
                        requestsStartingOver = true;
                    }
                    break;
                }
                j++;
            }
            i++;
        }
        Function<List<T>, Boolean> addAllFunction = (b) -> finalResult.addAll(b
                        .stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));

        addAllFunction.apply(l1);
        if (!selfMerge) {
            addAllFunction.apply(l2);
        }

        if(requestsStartingOver) {
            return merge(finalResult, finalResult, true);
        }
        return finalResult;
    }*/
}
