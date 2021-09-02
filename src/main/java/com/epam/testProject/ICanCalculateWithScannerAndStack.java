package com.epam.testProject;

import com.epam.testProject.calculatorClasses.ScannerDoppelganger;

import java.util.Deque;
import java.util.Scanner;

public interface ICanCalculateWithScannerAndStack {
    void defineOperationAndPutItsResultOnStack(ScannerDoppelganger scanner, Deque<Double> stack);

    double calculateSumAllTermsOnStack(Deque<Double> stack);
}
