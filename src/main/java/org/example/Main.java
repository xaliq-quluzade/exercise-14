package org.example;

public class Main {
    public static void main(String[] args) {
        ApplicationService applicationService = new ApplicationService(new CalculatorServiceImpl());
        int multiplied = applicationService.multiply(10, 2);
        int subtraction = applicationService.subtract(15, 10);
        int addition = applicationService.add(15, 10);
        int division = applicationService.divide(16, 10);
        System.out.println(multiplied);
        System.out.println(subtraction);
        System.out.println(addition);
        System.out.println(division);
    }
}
