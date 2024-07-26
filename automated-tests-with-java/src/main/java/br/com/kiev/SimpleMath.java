package br.com.kiev;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleMath {
    public Double sum(Double firstNumber, Double secondNumber) {
        return firstNumber + secondNumber;
    }

    public Double subtraction(Double firstNumber, Double secondNumber) {
        return firstNumber - secondNumber;
    }

    public Double multiplication(Double firstNumber, Double secondNumber) {
        return firstNumber * secondNumber;
    }

    public Double division(Double firstNumber, Double secondNumber) {
        if (firstNumber == 0.0D || secondNumber == 0.0D) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return firstNumber / secondNumber;
    }

    public Double mean(Double firstNumber, Double secondNumber) {
        return (firstNumber + secondNumber) / 2;
    }

    public Double squareRoot(Double number) {
        return Math.sqrt(number);
    }
}
