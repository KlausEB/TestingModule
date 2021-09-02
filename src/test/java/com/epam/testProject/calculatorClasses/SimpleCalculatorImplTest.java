package com.epam.testProject.calculatorClasses;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mockito.*;
import org.mockito.stubbing.Answer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleCalculatorImplTest {

    @InjectMocks
    @Spy
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
    public void calculateExpression_True_StringExpressionTenPlusFiveEqualsFifteen() {
        //GIVEN
        ScannerDoppelganger scanner = mock(ScannerDoppelganger.class);
        doAnswer(new Answer<Boolean>() {
            int count = 0;
            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                return count++ < 2;
            }
        }).when(scanner).hasNext();
        doAnswer(new Answer<Boolean>() {
            int count = 0;
            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                return count++ == 0;
            }
        }).when(scanner).hasNextDouble();
        doReturn(10.0).when(scanner).nextDouble();
        doNothing().when(calculator).defineOperationAndPutItsResultOnStack(Matchers.<ScannerDoppelganger>any(), Matchers.<Deque<Double>>any());
        doReturn(15.0).when(calculator).calculateExpression(scanner);

        //WHEN
        double resultOfExpression = calculator.calculateExpression(scanner);

        //THEN
        assertEquals(15, resultOfExpression, 0.0);
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
    public void calculateSumAllTermsOnStack_True_CorrectSumForElementsOnStack() {
        //GIVEN
        Deque<Double> termStack = mock(Deque.class);
        doAnswer(new Answer<Double>() {
            int count = 0;
            @Override
            public Double answer(InvocationOnMock invocationOnMock) throws Throwable {
                if (count == 0){
                    count++;
                    return 1.0;
                } else if (count == 1){
                    count++;
                    return 2.5;
                } else {
                    return 0.3;
                }
            }
        }).when(termStack).removeFirst();
        doAnswer(new Answer<Boolean>() {
            int count = 0;
            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                return count++ > 2;
            }
        }).when(termStack).isEmpty();

        //WHEN
        double sumStack = calculator.calculateSumAllTermsOnStack(termStack);

        //THEN
        assertEquals(3.8, sumStack, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void defineOperationAndPutItsResultOnStack_IllegalArgumentException_IncorrectOperationInStringExpression() {
        //GIVEN
        ScannerDoppelganger scanner = mock(ScannerDoppelganger.class);
        Deque<Double> termStack = mock(Deque.class);
        doReturn("^").when(scanner).next();
        doReturn(true).when(scanner).hasNextDouble();
        doReturn(3.0).when(scanner).nextDouble();

        //WHEN
        calculator.defineOperationAndPutItsResultOnStack(scanner, termStack);
    }

    @Test
    public void defineOperationAndPutItsResultOnStack_True_CorrectWorkingWithStack() {
        //GIVEN
        SimpleCalculatorImpl spyCalculator = spy(SimpleCalculatorImpl.class);
        Deque<Double> termStack = mock(Deque.class);
        ScannerDoppelganger scanner = mock(ScannerDoppelganger.class);
        doReturn(6.0).when(spyCalculator).takeMultiplication(2.0, 3.0);
        doNothing().when(termStack).addFirst(6.0);
        doReturn("*").when(scanner).next();
        doReturn(true).when(scanner).hasNextDouble();
        doReturn(3.0).when(scanner).nextDouble();
        doAnswer(new Answer<Double>() {
            int count = 0;
            @Override
            public Double answer(InvocationOnMock invocationOnMock) throws Throwable {
                if(count++ == 0){
                    return 2.0;
                } else {
                    return 6.0;
                }
            }
        }).when(termStack).removeFirst();

        //WHEN
        spyCalculator.defineOperationAndPutItsResultOnStack(scanner, termStack);

        //THEN
        assertEquals(6.0, termStack.removeFirst(), 0.0);
        verify(spyCalculator).takeMultiplication(2.0, 3.0);
    }

}