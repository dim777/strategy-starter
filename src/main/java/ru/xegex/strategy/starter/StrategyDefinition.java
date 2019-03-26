package ru.xegex.strategy.starter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This ...
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StrategyDefinition {
    String STRATEGY_NAME_FIELD = "name";
    String STRATEGY_NAME_DEFAULT_VALUE = "";
    String STRATEGY_SECURITIES_FIELD = "securities";

    String name() default STRATEGY_NAME_DEFAULT_VALUE;

    String[] securities();
}
