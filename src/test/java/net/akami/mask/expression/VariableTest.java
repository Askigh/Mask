package net.akami.mask.expression;

import net.akami.mask.handler.Multiplier;
import net.akami.mask.overlay.ExponentOverlay;
import net.akami.mask.utils.VariableComparator;
import net.akami.mask.utils.VariableUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.akami.mask.core.MaskContext.DEFAULT;
import static org.assertj.core.api.Assertions.assertThat;

public class VariableTest {

    private final Multiplier multiplier = new Multiplier(DEFAULT);

    @Test
    public void combineVars() {
        assertCombineVars(new char[]{'y'}, new char[]{'y'}, "y^2.0");
        assertCombineVars(new char[]{'x'}, new char[]{'y'}, "xy");
        assertCombineVars(new char[]{'x', 'y'}, new char[]{'x', 'y'}, "x^2.0y^2.0");
    }

    @Test
    public void dissociateVars() {
        ExponentOverlay exp1 = ExponentOverlay.fromExpression(Expression.of(5));
        ExponentOverlay exp2 = ExponentOverlay.fromExpression(Expression.of(2.5f));
        ExponentOverlay exp3 = ExponentOverlay.fromExpression(Expression.of('y'));
        Monomial simpleX = new Monomial('x', DEFAULT);

        ComplexVariable[] simple = {new ComplexVariable(simpleX, exp1)};
        ComplexVariable[] fraction = {new ComplexVariable(simpleX, exp2)};
        ComplexVariable[] irreducible = {new ComplexVariable(simpleX, exp3)};
        assertDissociateVars(Arrays.asList(simple), "xxxxx");
        assertDissociateVars(Arrays.asList(fraction), "xxx^0.5");
        assertDissociateVars(Arrays.asList(irreducible), "x^y");
    }

    @Test
    public void sortingVars() {
        SingleCharVariable simple = new SingleCharVariable('x', DEFAULT);
        ExponentOverlay squared = ExponentOverlay.fromExpression(Expression.of(2));
        ComplexVariable complex = new ComplexVariable(new Monomial('x',DEFAULT), squared);
        List<Variable> list = Arrays.asList(simple, complex);
        list.sort(VariableComparator.COMPARATOR);
        System.out.println(list);
    }

    private void assertDissociateVars(List<Variable> input, String result) {
        List<String> converted = VariableUtils.dissociate(input)
                .stream()
                .map(Variable::getExpression)
                .collect(Collectors.toList());
        assertThat(String.join("", converted)).isEqualTo(result);
    }

    private void assertCombineVars(char[] v1, char[] v2, String result) {
        List<Variable> variables = VariableUtils.combine(get(v1), get(v2), DEFAULT, false);
        List<String> converted = variables
                .stream()
                .map(Variable::getExpression)
                .collect(Collectors.toList());

        assertThat(String.join("", converted)).isEqualTo(result);
    }

    @Test
    public void getComplexExpressionTest() {

        Monomial m = new Monomial('x', DEFAULT);
        ComplexVariable c1 = new ComplexVariable(m);
        assertThat(c1.getExpression()).isEqualTo("x");
    }

    /*@Test
    public void recursiveComplexTest() {
        Expression result = ReducerFactory.reduce("y^4.0+5.0x");
        ComplexVariable complex = new ComplexVariable(new Monomial(1, new ComplexVariable(result.getElements())));
        Monomial m1 = new NumberElement(4);
        Monomial m2 = new Monomial(1, complex);
        assertThat(multiplier.simpleMult(m1, m2)).isEqualTo("4.0y^4.0+20.0x");
    }*/

    private List<Variable> get(char... input) {
        SingleCharVariable[] vars = new SingleCharVariable[input.length];

        int i = 0;
        for(char s : input) {
            vars[i++] = new SingleCharVariable(s, DEFAULT);
        }
        return Arrays.asList(vars);
    }
}