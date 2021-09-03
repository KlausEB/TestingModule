package com.epam.testProject.impl;

import java.util.Locale;
import java.util.Scanner;

public class ScannerDoppelganger {
    Scanner scanner;

    public ScannerDoppelganger(String string) {
        this.scanner = new Scanner(string);
        scanner.useLocale(Locale.US);
    }

    public boolean hasNext() {
        return scanner.hasNext();
    }

    public boolean hasNextDouble() {
        return scanner.hasNextDouble();
    }

    public double nextDouble() {
        return scanner.nextDouble();
    }

    public String next() {
        return scanner.next();
    }
}
