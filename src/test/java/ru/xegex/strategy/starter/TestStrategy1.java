package ru.xegex.strategy.starter;

import lombok.Getter;
import ru.xegex.strategy.starter.model.Tick;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Flow;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Test strategy
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
@StrategyDefinition(name = "TestStrategy", securities = {"LKOH", "SBER"})
@Getter
public class TestStrategy1 implements Strategy<Tick> {
    private Map<Integer, Tick> ticks = new HashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    @Override
    public boolean process(Tick tick) {
        int oldValue = counter.get();
        int newValue = oldValue + 1;

        while (true) {
            if (counter.compareAndSet(oldValue, newValue)) {
                ticks.put(oldValue, tick);
                return true;
            }
        }
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
