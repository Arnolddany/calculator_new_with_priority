package com.example.mainone;

public class Operation implements Comparable<Operation> {
    private double a;
    private double b;
    private char action;
    public Operation(double a, double b, char action) {
        this.a = a;
        this.b = b;
        this.action = action;
    }
    @Override
    public int compareTo(Operation operation) {
        if (this.action == '*' && operation.action == '-' || this.action == '/' && operation.action == '-' ||
            this.action == '*' && operation.action == '+' || this.action == '/' && operation.action == '+') {
            return -1;
    }
        if (this.action == '+' && operation.action == '*' || this.action == '-' && operation.action == '*' ||
            this.action == '+' && operation.action == '/' || this.action == '-' && operation.action == '/'){
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return  a + ", " + b + ", " + action + ";";
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getA() {
        return a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getB() {
        return b;
    }

    public char getAction() {
        return action;
    }
}
