package net.akami.mask.core;

import net.akami.mask.utils.ReducerFactory;

import java.util.Scanner;

public class MainTester {

    public static void main(String... args) {

        Mask curve = new Mask("x^2 + 3x + 4");
        MaskOperatorHandler handler = MaskOperatorHandler.DEFAULT;
        // Value already to true by default, if not changed
        handler.setCurrentToOut(true);
        String derivative = handler.compute(MaskDerivativeCalculator.class, curve, Mask.TEMP, 'x')
                .asExpression();

        System.out.println(derivative);


        Scanner sc = new Scanner(System.in);
        String expression;

        System.out.println("Next expression to reduce : ");
        while(!(expression = sc.nextLine()).isEmpty()) {
            long time = System.nanoTime();
            System.out.println("Result : "+ ReducerFactory.reduce(expression));
            float deltaTime = (System.nanoTime() - time) / 1000000000f;
            System.out.println("Calculations ended after "+deltaTime+" seconds");
            System.out.println("Next expression to reduce : ");
        }
        while(!(expression = sc.nextLine()).isEmpty()) {
            Mask.TEMP.reload(expression);
            handler.begin(Mask.TEMP);
            long time = System.nanoTime();
            System.out.println("Result : "+ handler.compute(MaskDerivativeCalculator.class, null, 'x').asExpression());
            float deltaTime = (System.nanoTime() - time) / 1000000000f;
            System.out.println("Calculations ended after "+deltaTime+" seconds");
            System.out.println("Next expression to reduce : ");
        }
    }
}
