package ru.xegex.strategy.starter;

import org.springframework.context.annotation.Import;

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
@Import(StrategyContainerConfigurationSelector.class)
@interface EnableStrategyContainer {
    String value() default "default";
}
