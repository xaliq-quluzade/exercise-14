package org.example;

public class ApplicationService {
    private final CalculatorService calculatorService;

    public ApplicationService(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public int add(int a, int b) {
        if (a < 0 || b < 0) {
            throw new ArithmeticException("ededlerden her hansisa biri sifirdan kicik ola bilmez");
        }
        return calculatorService.add(a, b);
    }

    public int multiply(int a, int b) {
        if (a > 4 && b > 6) {
            throw new ArithmeticException("eyni zamanda a 4-den boyuk ve b 6-dan boyuk ola bilmez");
        }
        return calculatorService.multiply(a, b);
    }

    public int subtract(int a, int b) {
        var result = calculatorService.subtract(a, b);
        if (result == 2 || result == 4 || result == 6 || result == 8) {
            throw new ArithmeticException("netice 2, 4, 6, 8 ola bilmez");
        }
        return result;
    }

    public int divide(int a, int b) {
        if (a % 2 == 1 || b % 2 == 1) {
            throw new ArithmeticException("ededlerin her ikisi 2-ye qaliqsiz bolunmelidir");
        }
        return calculatorService.divide(a, b);
    }

}
