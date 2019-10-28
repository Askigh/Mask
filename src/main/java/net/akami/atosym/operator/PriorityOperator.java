package net.akami.atosym.operator;

import net.akami.atosym.expression.MathObject;

import java.util.Collections;
import java.util.List;

public class PriorityOperator extends MathOperator {

    public PriorityOperator() {
        super(Collections.singletonList("priority"), 1);
    }

    // No operation needs to be done, (3+2) = 3+2
    @Override
    protected MathObject operate(List<MathObject> input) {
        return input.get(0);
    }
}