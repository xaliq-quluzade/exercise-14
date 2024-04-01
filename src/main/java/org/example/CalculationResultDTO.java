package org.example;

public record CalculationResultDTO(int a, int b, int result, String method) {
    @Override
    public String toString() {
        return "{" +
                "\n\t\t\ta:" + a +
                "\n\t\t\tb:" + b +
                "\n\t\t\tresult:" + result +
                "\n\t\t\tmethod:\"" + method + '\"' +
                "\n}";
    }
}
