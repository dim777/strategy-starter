package ru.xegex.strategy.starter;

import java.util.Collection;
import java.util.concurrent.Flow;

/**
 * This ...
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
interface StrategyContainer<T> extends Flow.Publisher<T> {
    boolean submit(T item);

    /**
     * Get registered strategies
     *
     * @return collection of strategies
     */
    Collection<Strategy<T>> strategies();
}
