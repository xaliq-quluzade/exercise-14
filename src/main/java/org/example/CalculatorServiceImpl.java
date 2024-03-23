package org.example;

public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public double divide(int a, int b) {
        return a / (double) b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }


}
