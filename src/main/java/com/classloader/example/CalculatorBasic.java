package com.classloader.example;


import com.classloader.ICalculator;

public class CalculatorBasic implements ICalculator {

    @Override
    public String calculate(String expression) {
        return expression;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

}
