package com.example.mainone;

public class Counting {
    private final char action;
    public Counting(char action) {
        this.action = action;
    }

    public double calculate(double a, double b) {
        double result = 0;
        if (action == '+') {
            result = a + b;
        } else if (action == '-') {
            result = a - b;
        } else if (action == '*') {
            result = a * b;
        } else if (action == '/') {
            result = a / b;
        }
        return result;
    }
}
