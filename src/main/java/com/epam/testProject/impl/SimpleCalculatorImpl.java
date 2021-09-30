package com.epam.testProject.impl;

import com.epam.testProject.ICanCalculateExpressions;
import com.epam.testProject.ICanCalculateWithScannerAndStack;
import com.epam.testProject.ISimpleCalculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleCalculatorImpl implements ISimpleCalculator, ICanCalculateExpressions, ICanCalculateWithScannerAndStack {

    @Override
    public double takeSum(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }

    @Override
    public double takeDivision(double divisible, double divisor) {
        return divisible / divisor;
    }

    @Override
    public double takeMultiplication(double firstNumber, double secondNumber) {
        return firstNumber * secondNumber;
    }

    @Override
    public double takeNegationOfNumber(double firstNumber) {
        return -firstNumber;
    }

    @Override
    public double takeSqrt(double number) {
        return Math.sqrt(number);
    }

    @Override
    public double calculateExpression(ScannerDoppelganger scanner) {
        Deque<Double> termStack = new ArrayDeque<>();
        while (scanner.hasNext()) {
            defineOperationAndPutItsResultOnStack(scanner, termStack);
        }
        return calculateSumAllTermsOnStack(termStack);
    }

    @Override
    public void defineOperationAndPutItsResultOnStack(ScannerDoppelganger scanner, Deque<Double> termStack) {
        if (scanner.hasNextDouble()) {
            termStack.addFirst(scanner.nextDouble());
        }

        String currentToken = scanner.next();

        if (currentToken.length() > 4 && currentToken.startsWith("sqrt")) {
            termStack.addFirst(takeSqrt(Double.parseDouble(currentToken.substring(4))));
            return;
        }

        double nextNumber;

        if (scanner.hasNextDouble()) {
            nextNumber = scanner.nextDouble();
        } else {
            nextNumber = takeSqrt(Double.parseDouble(scanner.next().substring(4)));
        }

        switch (currentToken) {
            case "+":
                termStack.addFirst(nextNumber);
                break;
            case "-":
                termStack.addFirst(takeNegationOfNumber(nextNumber));
                break;
            case "/":
                termStack.addFirst(takeDivision(termStack.removeFirst(), nextNumber));
                break;
            case "*":
                termStack.addFirst(takeMultiplication(termStack.removeFirst(), nextNumber));
                break;
            default:
                throw new IllegalArgumentException("Incorrect operation");
        }
    }

    @Override
    public double calculateSumAllTermsOnStack(Deque<Double> termStack) {
        double resultOfExpression = 0;
        while (!termStack.isEmpty()) {
            resultOfExpression = takeSum(termStack.removeFirst(), resultOfExpression);
        }
        return resultOfExpression;
    }
}
