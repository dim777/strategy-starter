package ru.xegex.strategy.starter;

import ru.xegex.strategy.starter.model.Security;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Flow;

/**
 * This ...
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
interface Strategy<T> extends Flow.Subscriber<T> {
    /**
     * Validate market open status
     *
     * @return open/close
     */
    default boolean isValidOpTime() {
        return true;
    }

    /**
     * Process new item
     *
     * @param item Tick/Quote/...
     * @return isProcessed
     */
    boolean process(T item);

    default String code() {
        return StrategyDefinition.STRATEGY_NAME_DEFAULT_VALUE;
    }

    /**
     * Subscribed securities
     *
     * @return list of secs
     */
    default List<Security> securities() {
        return Collections.emptyList();
    }

    @Override
    default void onNext(T item) {
        process(item);
    }
}
