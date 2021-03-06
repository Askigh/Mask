package net.akami.atosym.expression;

import net.akami.atosym.function.CosineFunction;
import net.akami.atosym.function.SineFunction;
import net.akami.atosym.function.TangentFunction;
import net.akami.atosym.handler.Multiplier;
import net.akami.atosym.overlay.ExpressionOverlay;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static net.akami.atosym.core.MaskContext.DEFAULT;
import static org.assertj.core.api.Assertions.assertThat;

public class ComplexVariableTest {

    private final Multiplier multiplier = new Multiplier(DEFAULT);

    @Test
    public void getExpressionTest() {

        List<Monomial> elements = Arrays.asList(
                new NumberElement(3), new Monomial(3, new SingleCharVariable('x', DEFAULT)));

        List<ExpressionOverlay> layers = Arrays.asList(
                new CosineFunction(DEFAULT),
                new SineFunction(DEFAULT),
                new TangentFunction(DEFAULT)
        );

        List<ExpressionOverlay> layers2 = Arrays.asList(
                new CosineFunction(DEFAULT),
                new TangentFunction(DEFAULT),
                new SineFunction(DEFAULT)
        );

        IntricateVariable cVar1 = new IntricateVariable(elements, layers);
        IntricateVariable cVar2 = new IntricateVariable(elements, layers);
        IntricateVariable cVar3 = new IntricateVariable(elements, layers2);
        assertThat(cVar1).isEqualTo(cVar2);
        assertThat(cVar1).isNotEqualTo(cVar3);
    }

    @Test
    public void composedMultTest() {

        List<ExpressionOverlay> layers = Arrays.asList(new CosineFunction(DEFAULT), new SineFunction(DEFAULT));
        Expression insights = multiplier.operate(Expression.of('x'), Expression.of('y'));
        Monomial m1 = new Monomial(2, new IntricateVariable(insights.getElements(), layers));
        Monomial m2 = new Monomial(5, new IntricateVariable(insights.getElements(), layers));

        Expression result = multiplier.operate(Expression.of(m1), Expression.of(m2));
        assertThat(result.toString()).isEqualTo("10.0sin(cos(xy))^2.0");
    }
}
