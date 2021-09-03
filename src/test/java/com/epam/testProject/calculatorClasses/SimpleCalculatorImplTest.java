package com.epam.testProject.calculatorClasses;

import com.epam.testProject.impl.ScannerDoppelganger;
import com.epam.testProject.impl.SimpleCalculatorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleCalculatorImplTest {

    @InjectMocks
    @Spy
    private SimpleCalculatorImpl calculator;

    @Mock
    private Deque<Double> termStack;

    @Mock
    private ScannerDoppelganger scanner;

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
    public void calculateExpression_True_StringExpressionTenPlusFiveEqualsFifteen() {
        //GIVEN
        doReturn(15.0).when(calculator).calculateSumAllTermsOnStack(Matchers.<Deque<Double>>any());

        //WHEN
        double resultOfExpression = calculator.calculateExpression(scanner);

        //THEN
        assertEquals(15, resultOfExpression, 0.0);
        verify(scanner).hasNext();
    }

    @Test
    public void calculateExpression_True_FinalStringExpressionEqualsCorrectValue() {
        //GIVEN
        ScannerDoppelganger expression = spy(new ScannerDoppelganger("2 + 3 * 45.3 * 90 + 20 - 8 / 20 - sqrt4"));

        //WHEN
        double resultOfExpression = calculator.calculateExpression(expression);

        //THEN
        assertEquals(12250.6, resultOfExpression, 0.1);
    }

    @Test
    public void calculateSumAllTermsOnStack_True_EmptyStackSumEqualsZero() {
        //GIVEN
        doReturn(true).when(termStack).isEmpty();

        //WHEN
        double sumStack = calculator.calculateSumAllTermsOnStack(termStack);

        //THEN
        assertEquals(0.0, sumStack, 0.0);
    }

    @Test
    public void calculateSumAllTermsOnStack_True_CorrectSumForElementsOnStack() {
        //GIVEN
        Deque<Double> stack = new ArrayDeque<>();
        stack.addFirst(1.7);
        stack.addFirst(2.0);
        stack.addFirst(0.1);


        //WHEN
        double sumStack = calculator.calculateSumAllTermsOnStack(stack);

        //THEN
        assertEquals(3.8, sumStack, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void defineOperationAndPutItsResultOnStack_IllegalArgumentException_IncorrectOperationInStringExpression() {
        //GIVEN
        doReturn("^").when(scanner).next();
        doReturn(true).when(scanner).hasNextDouble();
        doReturn(3.0).when(scanner).nextDouble();

        //WHEN
        calculator.defineOperationAndPutItsResultOnStack(scanner, termStack);
    }

    @Test
    public void defineOperationAndPutItsResultOnStack_True_CorrectWorkingWithStack() {
        //GIVEN
        doReturn(true).when(scanner).hasNextDouble();
        doReturn("*").when(scanner).next();
        double firstNumber = 2.0;
        double secondNumber = 3.0;
        doReturn(firstNumber).when(termStack).removeFirst();
        doReturn(secondNumber).when(scanner).nextDouble();
        doNothing().when(termStack).addFirst(anyDouble());

        //WHEN
        calculator.defineOperationAndPutItsResultOnStack(scanner, termStack);

        //THEN
        verify(calculator).takeMultiplication(firstNumber, secondNumber);
    }

}