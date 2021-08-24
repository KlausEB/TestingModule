package com.epam.testProject.calculatorClasses;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleCalculatorImplTest {

    @InjectMocks
    private SimpleCalculatorImpl calculator;

    @Test
    public void takeAddition_True_OnePlusOneEqualsTwo() {
        //GIVEN
        double number = 1;

        //WHEN
        double sum = calculator.takeSum(number, number);

        //THEN
        assertEquals(2, sum, 0.0);
    }


    @Test
    public void takeDivision_True_FiveDivideTwoEqualsTwoPointFive() {
        //GIVEN
        double divisible = 5;
        double divisor = 2;

        //WHEN
        double quotient = calculator.takeDivision(divisible, divisor);

        //THEN
        assertEquals(2.5, quotient, 0.0);
    }

    @Test
    public void takeMultiplication_True_TwoMultiplyTwoEqualsFour() {
        //GIVEN
        double number = 2;

        //WHEN
        double product = calculator.takeMultiplication(number, number);

        //THEN
        assertEquals(4, product, 0.0);
    }

    @Test
    public void takeNegationOfNumber_True_NegationOfThreeEqualsMinusThree() {
        //GIVEN
        double number = 3;

        //WHEN
        double negativeNumber = calculator.takeNegationOfNumber(number);

        //THEN
        assertEquals(-3, negativeNumber, 0.0);
    }

    @Test
    public void takeSqrt_True_SqrtOfNineEqualsThree() {
        //GIVEN
        double number = 9;

        //WHEN
        double sqrt = calculator.takeSqrt(number);

        //THEN
        assertEquals(3, sqrt, 0.0);
    }

    @Test
    public void calculateExpression_True_StringExpressionEqualsTwoPointFive(){
        //GIVEN
        String expression = "2.5";

        //WHEN
        double resultOfExpression = calculator.calculateExpression(expression);

        //THEN
        assertEquals(2.5, resultOfExpression, 0.0);
    }

    @Test
    public void calculateExpression_True_StringExpressionTenPlusFiveEqualsFifteen(){
        //GIVEN
        String expression = "10 + 5";

        //WHEN
        double resultOfExpression = calculator.calculateExpression(expression);

        //THEN
        assertEquals(15, resultOfExpression, 0.0);
    }

    @Test
    public void calculateExpression_True_StringExpressionSixMinusTwoEqualsFour(){
        //GIVEN
        String expression = "6 - 2";

        //WHEN
        double resultOfExpression = calculator.calculateExpression(expression);

        //THEN
        assertEquals(4, resultOfExpression, 0.0);
    }

    @Test
    public void calculateExpression_True_StringExpressionSixDivideTwoEqualsThree(){
        //GIVEN
        String expression = "6 / 2";

        //WHEN
        double resultOfExpression = calculator.calculateExpression(expression);

        //THEN
        assertEquals(3, resultOfExpression, 0.0);
    }

    @Test
    public void calculateExpression_True_StringExpressionSixDivideTwoAndDivideThreeEqualsOne(){
        //GIVEN
        String expression = "6 / 2 / 3";

        //WHEN
        double resultOfExpression = calculator.calculateExpression(expression);

        //THEN
        assertEquals(1, resultOfExpression, 0.0);
    }

    @Test
    public void calculateExpression_True_StringExpressionSixMultiplyTwoEqualsTwelve(){
        //GIVEN
        String expression = "6 * 2";

        //WHEN
        double resultOfExpression = calculator.calculateExpression(expression);

        //THEN
        assertEquals(12, resultOfExpression, 0.0);
    }

    @Test
    public void calculateExpression_True_StringExpressionSqrtOfSixteenEqualsFour(){
        //GIVEN
        String expression = "sqrt16";

        //WHEN
        double resultOfExpression = calculator.calculateExpression(expression);

        //THEN
        assertEquals(4, resultOfExpression, 0.0);
    }

    @Test
    public void calculateExpression_True_StringExpressionOneMultiplySqrtOfFourEqualsTwo(){
        //GIVEN
        String expression = "1 * sqrt4";

        //WHEN
        double resultOfExpression = calculator.calculateExpression(expression);

        //THEN
        assertEquals(2, resultOfExpression, 0.0);
    }

    @Test
    public void calculateExpression_True_FinalStringExpressionEqualsCorrectValue(){
        //GIVEN
        String expression = "2 + 3 * 45.3 * 90 + 20 - 8 / 20 - sqrt4";

        //WHEN
        double resultOfExpression = calculator.calculateExpression(expression);

        //THEN
        assertEquals(12250.6, resultOfExpression, 0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateExpression_IllegalArgumentException_IncorrectOperationInStringExpression(){
        //GIVEN
        String expression = "2 ^ 3";

        //WHEN
        double resultOfExpression = calculator.calculateExpression(expression);
    }
}