package com.epam.testProject;

public interface ISimpleCalculator {
    double takeSum(double firstNumber, double secondNumber);

    double takeDivision(double firstNumber, double secondNumber);

    double takeMultiplication(double firstNumber, double secondNumber);

    double takeNegationOfNumber(double firstNumber);

    double takeSqrt(double number);

    double calculateExpression(String expression);
}
