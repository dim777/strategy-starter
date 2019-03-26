package ru.xegex.strategy.starter.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * This ...
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
public class FactorialTest {
    @Test
    public void factorial0() {
        int factorial = Factorial.factorial(0);
        Assert.assertEquals(1, factorial);
    }

    @Test
    public void factorial1() {
        int factorial = Factorial.factorial(1);
        Assert.assertEquals(1, factorial);
    }

    @Test
    public void factorial3() {
        int factorial = Factorial.factorial(3);
        Assert.assertEquals(6, factorial);
    }

    @Test
    public void factorial5() {
        int factorial = Factorial.factorial(5);
        Assert.assertEquals(120, factorial);
    }

    @Test(expected = AssertionError.class)
    public void factorialEx() {
        int factorial = Factorial.factorial(-5);
    }
}