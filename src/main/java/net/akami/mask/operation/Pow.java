package net.akami.mask.operation;

import net.akami.mask.utils.ExpressionUtils;
import net.akami.mask.utils.FormatterFactory;
import net.akami.mask.utils.MathUtils;

public class Pow extends BinaryOperationHandler {

    private static final Pow INSTANCE = new Pow();

    @Override
    public String operate(String a, String b) {
        LOGGER.debug("Pow operation process between {} and {} : \n", a, b);

        String aVars = ExpressionUtils.toVariables(a);
        String bVars = ExpressionUtils.toVariables(b);

        LOGGER.debug("aVars : {}, bVars : {}", aVars, bVars);
        if (aVars.length() == 0 && bVars.length() == 0) {
            String result = String.valueOf(Math.pow(Float.parseFloat(a), Float.parseFloat(b)));
            LOGGER.info("No variable found, return a^b value : {}", result);
            return result;
        }
        float powValue;
        // If pow value is too high, there is no point in developing the entire expression
        if (bVars.length() != 0 || (powValue = Float.parseFloat(b)) > 199) {
            LOGGER.info("Pow value contains variables or pow value is greater than 9. Returns a^b");
            return a + "^" + (ExpressionUtils.isReduced(b) ? b : "(" + b + ")");
        }

        clearBuilder();
        StringBuilder builder = new StringBuilder();
        builder.append(a);
        for (int i = 1; i < powValue; i++) {
            builder.replace(0, builder.length(), Multiplication.getInstance().rawOperate(builder.toString(), a));
            LOGGER.info("{} steps left. Currently : {}", powValue - i - 1, builder.toString());
        }
        return builder.toString();
    }

    @Override
    public String inFormat(String origin) {
        return FormatterFactory.removeFractions(origin);
    }

    @Override
    public String outFormat(String origin) {
        return MathUtils.cutSignificantZero(ExpressionUtils.addMultShortcut(origin));
    }

    public static Pow getInstance() {
        return INSTANCE;
    }
}
