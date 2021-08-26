package com.epam.testProject;

import java.util.Deque;
import java.util.Scanner;

public interface ICanCalculateWithScannerAndStack {
    void defineOperationAndPutItsResultOnStack(Scanner scanner, Deque<Double> stack);

    double calculateSumAllTermsOnStack(Deque<Double> stack);
}
