package com.epam.testProject;

import com.epam.testProject.impl.ScannerDoppelganger;

import java.util.Deque;

public interface ICanCalculateWithScannerAndStack {
    void defineOperationAndPutItsResultOnStack(ScannerDoppelganger scanner, Deque<Double> stack);

    double calculateSumAllTermsOnStack(Deque<Double> stack);
}
