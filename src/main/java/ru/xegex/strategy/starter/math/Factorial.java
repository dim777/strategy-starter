package ru.xegex.strategy.starter.math;

/**
 * This ...
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
public final class Factorial {
    public static int factorial(int n) {
        assert n >= 0;
        if (n == 0) return 1;
        if (n == 1) return 1;
        return n * (factorial(n - 1));
    }
}
