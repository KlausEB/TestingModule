package com.epam.testProject.calculatorClasses;

import com.epam.testProject.ISimpleCalculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;
import java.util.Scanner;

public class SimpleCalculatorImpl implements ISimpleCalculator {

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
    public double calculateExpression(String expression) {
        Deque<Double> termStack = new ArrayDeque<>();
        Scanner scanner = new Scanner(expression);
        scanner.useLocale(Locale.US);
        while (scanner.hasNext()){
            if (scanner.hasNextDouble()){
                termStack.addFirst(scanner.nextDouble());
            } else {
                defineOperationAndPutItsResultOnStack(scanner, termStack);
            }
        }
        return calculateSumAllTermsOnStack(termStack);
    }

    private void defineOperationAndPutItsResultOnStack(Scanner scanner, Deque<Double> termStack){
        String currentToken = scanner.next();

        if (currentToken.length() > 4 && currentToken.substring(0, 4).equals("sqrt")) {
            termStack.addFirst(takeSqrt(Double.parseDouble(currentToken.substring(4))));
            return;
        }

        double nextNumber;

        if (scanner.hasNextDouble()){
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

    private double calculateSumAllTermsOnStack(Deque<Double> termStack){
        double resultOfExpression = 0;
        for (double currentNumber : termStack) {
            resultOfExpression = takeSum(resultOfExpression, currentNumber);
        }
        return resultOfExpression;
    }
}
