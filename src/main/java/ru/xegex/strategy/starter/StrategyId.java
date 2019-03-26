package ru.xegex.strategy.starter;

import lombok.Getter;

/**
 * This ...
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
@Getter
public class StrategyId {
    private final String code;

    public StrategyId(String code) {
        this.code = code;
    }
}
