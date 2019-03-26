package ru.xegex.strategy.starter;

/**
 * This is common ex for strategies error
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
public class StrategyEx extends RuntimeException {
    public StrategyEx() {
    }

    public StrategyEx(String message) {
        super(message);
    }

    public StrategyEx(String message, Throwable cause) {
        super(message, cause);
    }

    public StrategyEx(Throwable cause) {
        super(cause);
    }

    public StrategyEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
