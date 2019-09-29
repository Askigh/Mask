package net.akami.atosym.handler;

import net.akami.atosym.core.MaskContext;
import net.akami.atosym.expression.MathObject;
import net.akami.atosym.function.MathOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
    To not confuse :

        Mult is a binary operator, which means that he can only computes a*b, not a*b*c*...
        However, it does not mean that the mult function can only store 2 arguments.

        mult(a, mult(b,c)) can be computed, and will result in mult(a,b,c)
 */
public abstract class BinaryOperator extends MathOperator {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BinaryOperator.class);

    public BinaryOperator(String name) {
        super(name, 2);
    }

    @Override
    protected MathObject operate(MathObject... input) {
        return binaryOperate(input[0], input[1]);
    }

    protected abstract MathObject binaryOperate(MathObject a, MathObject b);

    public static Set<BinaryOperator> generateDefaultBinaryOperators(MaskContext context) {
        return new HashSet<>(Arrays.asList(
                new SumOperator(context),
                new SubOperator(context),
                new MultOperator(context),
                new DivOperator(context),
                new PowerOperator(context)
        ));
    }
}