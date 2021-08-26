package com.epam.testProject;

import com.epam.testProject.calculatorClasses.SimpleCalculatorImpl;

public class App {
    public static void main(String[] args) {
        ICanCalculateExpressions calculator = new SimpleCalculatorImpl();
        System.out.println(calculator.calculateExpression("2 + 3 * 45.3 * 90 + 20 - 8 / 20 - sqrt4"));
    }
}
