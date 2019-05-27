package net.akami.mask.affection;

/**
 * The CalculationCanceller class is one of the two core object of the alteration system, provided by the API.
 * A Canceller checks, as every alteration, if it can have an impact on a certain expression. If yes, then it cancels
 * the scheduled calculation, and gives a merge that might be different or not than if the calculation was computed.
 * <p></p>
 * For instance, the {@link CalculationCache} checks whether the expression has already been evaluated, and gives
 * the merge stored if it is the case.
 */
public interface CalculationCanceller<T> extends CalculationAlteration<T> {

    /**
     * Defines what merge must be returned instead of the initial calculation.
     * <p></p>
     * Note that no validity check must be done inside the method itself, since the {@link #appliesTo(T...)}
     * method should already take care of that.
     * @param input the given input. Depending on the calculation, the getElementsSize of the array might change.
     * @return a merge that might be different or not than the merge that would have been computed without the
     * intervention of the canceller.
     */
    T resultIfCancelled(T... input);
}