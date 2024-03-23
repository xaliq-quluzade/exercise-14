package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceUnitTest {

    @Mock
    private CalculatorServiceImpl calculatorService;

    @InjectMocks
    private ApplicationService applicationService;

    @Test
    void add_success() {
        when(calculatorService.add(anyInt(), anyInt())).thenReturn(20);
        int actual = applicationService.add(6, 14);
        verify(calculatorService, times(1)).add(6, 14);
        Assertions.assertEquals(20, actual);
    }

    @Test
    void divide_success() {
        when(calculatorService.divide(anyInt(), anyInt())).thenReturn(3.2);
        double actual = applicationService.divide(32, 10);
        verify(calculatorService, times(1)).divide(32, 10);
        Assertions.assertEquals(3.2, actual);
    }

    @Test
    void multiply_success() {
        when(calculatorService.multiply(anyInt(), anyInt())).thenReturn(12);
        int actual = applicationService.multiply(3, 4);
        verify(calculatorService, times(1)).multiply(3, 4);
        Assertions.assertEquals(12, actual);
    }

    @Test
    void subtract_success() {
        when(calculatorService.subtract(anyInt(), anyInt())).thenReturn(7);
        int actual = applicationService.subtract(10, 3);
        verify(calculatorService, times(1)).subtract(10, 3);
        Assertions.assertEquals(7, actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"-5,7", "6,-8", "-10,-20"}, delimiter = ',')
    void add_throws(int a, int b) {
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            applicationService.add(a, b);
        });
        Assertions.assertInstanceOf(ArithmeticException.class, exception);
    }

    @ParameterizedTest
    @CsvSource(value = {"9,2", "10,5", "9,3"}, delimiter = ',')
    void divide_throws(int a, int b) {
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            applicationService.divide(a, b);
        });
        Assertions.assertInstanceOf(ArithmeticException.class, exception);
    }

    @ParameterizedTest
    @CsvSource(value = {"8,6,2", "8,4,4", "10,4,6", "10,2,8"}, delimiter = ',')
    void subtract_throws(int a, int b, int result) {
        when(calculatorService.subtract(a, b)).thenReturn(result);
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            applicationService.subtract(a, b);
        });
        Assertions.assertInstanceOf(ArithmeticException.class, exception);
        verify(calculatorService, times(1)).subtract(a, b);
    }

    @ParameterizedTest
    @CsvSource(value = {"5,7", "6,8", "10,20"}, delimiter = ',')
    void multiply_throws(int a, int b) {
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            applicationService.multiply(a, b);
        });
        Assertions.assertInstanceOf(ArithmeticException.class, exception);
    }

}
