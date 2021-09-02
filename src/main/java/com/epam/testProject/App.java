package com.epam.testProject;

import com.epam.testProject.calculatorClasses.ScannerDoppelganger;
import com.epam.testProject.calculatorClasses.SimpleCalculatorImpl;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ICanCalculateExpressions calculator = new SimpleCalculatorImpl();
        ScannerDoppelganger scanner = new ScannerDoppelganger("2 + 3 * 45.3 * 90 + 20 - 8 / 20 - sqrt4");
        System.out.println(calculator.calculateExpression(scanner));
    }
}
