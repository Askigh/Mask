package net.akami.atosym.handler.sign;

import net.akami.atosym.alteration.CalculationCache;
import net.akami.atosym.core.MaskContext;
import net.akami.atosym.expression.MathObject;
import net.akami.atosym.utils.MathUtils;

import java.util.Objects;

/**
 * A BinaryOperationSign links a char to a defined operation, taking two strings as inputs, and computing a single
 * string out of the input. Binary operations also have a priority level, which determines whether an operation has a
 * higher priority than another. <br> <br>
 *
 * For instance, the sum operator has a lower priority level than the mult operator, but an equal priority level
 * to the subtract operator's. <br>  <br>
 *
 * The operation is defined using method referencing, via the functional sub-interface {@link BinaryMathCalculation}.
 * All referenced methods used belong to the {@link MathUtils} class created for this purpose. <br>
 * The None operator allows the user to deal with the different {@link BinaryOperationSign}s two by two, without
 * getting any {@link IndexOutOfBoundsException}. See {@link net.akami.atosym.tree.CalculationTree} for more details
 * about signs dealing and branch splitting. Note that the None operator might be removed in the future.
 *
 * @see QuaternaryOperationSign for other operations
 */
public enum BinaryOperationSign {

    /**
     * Sum operator, computes a + b using the {@link MathUtils#sum(MathObject, MathObject)} method.
     * <br>
     * This operator can deal with a chain of additions / subtractions. Therefore, there will not be any problem
     * if the a or b value happens to be a polynomial.
     * <br>
     *
     * Examples :
     * <br><pre>
     * ---a---|---b---
     *    4   +   5     = 9
     *   4x   +   5     = 4x+5
     *  3+2x  +   x     = 3+3x
     *  3y-2  +   y     = 4y-2
     * </pre>
     */
    SUM('+', MathUtils::sum, 0),

    /**
     * Subtraction operator, computes a - b using the {@link MathUtils#subtract(MathObject, MathObject)} method.
     * <br>
     * This operator works the same way as the {@code SUM} operator, except that it multiply all the monomials
     * found in the {@code b} input by -1.
     * <br>
     *
     * Examples :
     * <br><pre>
     * ---a---|---b---
     *    4   -   5     = -1
     *   4x   -   5     = 4x-5
     *  3+2x  -   x     = 3+x
     *  3y-2  -   y     = 2y-2
     * </pre>
     */
    SUBTRACT('-', MathUtils::subtract, 0),

    /**
     * Mult operator, computes a * b using the {@link MathUtils#mult(MathObject, MathObject)} method.
     * <br>
     * It also supports polynomial multiplication (not only the monomial ones) thus distributivity.
     * <br>
     * Note that the unknown parts are always sorted alphabetically.
     * <br>
     * Examples :
     * <br><pre>
     * ---a---|---b---
     *    4   *   5     = 20
     *   4x   *   5     = 20x
     *  3+2x  *   x     = 3x+2x^2
     *  3y-2  *  x+y    = 3xy+3y^2-2x-2y
     * </pre>
     */
    MULT('*', MathUtils::mult, 1),

    /**
     * Division operator, computes a / b using the {@link MathUtils#divide(MathObject, MathObject)} method.
     *
     * Note that only the following division types are supported by now :
     *
     * <ul>
     * <li> Numeric a and b </li>
     * <li> Monomial / Numeric a and b </li>
     * <li> Polynomial a and Monomial / Numeric b </li>
     * </ul>
     * In other words, b can not be a polynomial, even if simplifications could technically be performed.
     * <br>
     * Examples :
     * <br><pre>
     * ---a---|---b---
     *    4   /   5     = 0.8
     *   4x   /   5     = 4x/5
     *  3+2x  /   x     = 3/x + 2
     *  3y-2  /  x+y    = Not calculable yet. Returns (3y-2)/(x+y)
     * </pre>
     */
    DIVIDE('/', MathUtils::divide, 1),

    /**
     * Pow operator, computes a ^ b using the {@link MathUtils#pow(MathObject, MathObject)} method.
     * <br>
     *
     * The operator performs the calculation only if it is mathematically possible, therefore any monomial / polynomial
     * to the power of a non integer number, as well as any algebraic exponent won't be calculated, thus the returned
     * merge will be {@code a^b, (a)^b, a^(b) or (a)^(b)} depending of the case, so that the priority of operations is respected.
     * <br>
     *
     * The {@link MaskContext} also influences whether the calculation will be performed or not,
     * if {@code b} is an integer. In fact, the context (or more precisely the {@link CalculationCache})
     * determines the maximal value of {@code b} before cancelling the operation.
     * <br>
     * Examples :
     * <br><pre>
     * ---a---|---b---
     *    4   ^   5     = 1024
     *  4x+y  ^   4     = 256x^4+256x^3y+96x^2y^2+16xy^3+y^4
     *  3+2x  ^   x     = (3+2x)^x
     *  3y-2  ^  x+y    = (3y-2)^(x+y)
     * </pre>
     */
    POW('^', MathUtils::pow, 2);

    private char sign;
    private BinaryMathCalculation binaryFunction;
    private int priorityLevel;

    BinaryOperationSign(char sign, BinaryMathCalculation function, int priorityLevel) {
        this.sign = sign;
        this.binaryFunction = function;
        this.priorityLevel = priorityLevel;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public MathObject compute(MathObject a, MathObject b, MaskContext context) {
        Objects.requireNonNull(binaryFunction);
        return binaryFunction.compute(a, b, context);
    }

    public static BinaryOperationSign getBySign(char sign) {
        for(BinaryOperationSign operation : values()) {
            if(operation.sign == sign) {
                return operation;
            }
        }
        return null;
    }

    @FunctionalInterface
    public interface BinaryMathCalculation {
        MathObject compute(MathObject a, MathObject b, MaskContext context);
    }
}
