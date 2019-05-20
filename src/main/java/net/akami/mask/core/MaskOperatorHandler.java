package net.akami.mask.core;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Manager for the {@link MaskOperator} instances. Allows the user to call the {@code compute()} methods with
 * the {@link MaskOperator#getClass()} type the want.
 */
public class MaskOperatorHandler {

    public static final MaskOperatorHandler DEFAULT = new MaskOperatorHandler();

    private Mask current;
    private MaskContext context;
    private List<MaskOperator> operators;
    private boolean setToOut = true;

    public MaskOperatorHandler() {
        this.context = MaskContext.DEFAULT;
        this.operators = MaskOperator.defaultOperators();
    }

    public void begin(Mask current) {
        this.current = current;
    }

    public void setCurrentToOut(boolean setToOut) {
        this.setToOut = setToOut;
    }

    public <E, T extends MaskOperator<E>> MaskOperatorHandler compute(Class<T> op, Mask out, E extraData) {
        return compute(op, current, out, extraData, null);
    }

    public <E, T extends MaskOperator<E>> MaskOperatorHandler compute(Class<T> op, Mask in, Mask out, E extraData) {
        return compute(op, in, out, extraData,null);
    }

    public <E, T extends MaskOperator<E>> MaskOperatorHandler compute(Class<T> op, Mask out,
                                                                      E extraData, Consumer<Mask> outAction) {
        return compute(op, current, out, extraData, outAction);
    }

    public <E, T extends MaskOperator<E>> MaskOperatorHandler compute(Class<T> op, Mask in, Mask out,
                                                                      E extraData, Consumer<Mask> outAction) {
        Objects.requireNonNull(in);

        if(out == null)
            out = Mask.TEMP;

        MaskOperator operator = findByType(op);
        operator.compute(in, out, extraData, this.context);
        if(outAction != null) {
            outAction.accept(out);
        }

        if(setToOut)
            this.current = out;

        return this;
    }

    private MaskOperator findByType(Class<? extends MaskOperator> type) {
        for(MaskOperator operator : operators) {
            if(operator.getClass().equals(type))
                return operator;
        }
        return null;
    }

    public String asExpression() {
        Objects.requireNonNull(current);
        return current.getExpression();
    }

    public void end() {
        this.current = null;
    }

    public MaskContext getContext() {
        return context;
    }
}
