package ru.xegex.strategy.starter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.xegex.strategy.starter.model.Security;
import ru.xegex.strategy.starter.model.Tick;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Flow;

/**
 * Default strategy container implementation
 * <p>Flow API Publisher interface implemented</p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
@Component
public class DefaultTicksStrategyContainer implements StrategyContainer<Tick> {
    private final Map<StrategyId, Strategy<Tick>> strategies;

    public DefaultTicksStrategyContainer() {
        this.strategies = new ConcurrentHashMap<>();
    }

    @Override
    public boolean submit(Tick tick) {
        final Result result = new Result();
        strategies.values().parallelStream().forEach(s -> {
            List<Security> securities = s.securities();
            if (securities.contains(tick.getSecurity())) {
                if (!s.process(tick)) {
                    result.setOk(false);
                }
            }
        });
        return result.isOk();
    }

    @Override
    public Collection<Strategy<Tick>> strategies() {
        return strategies.values();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void subscribe(Flow.Subscriber<? super Tick> subscriber) {
        Strategy<Tick> strategy = (Strategy<Tick>) subscriber;
        StrategyId strategyId = new StrategyId(strategy.code());
        strategies.put(strategyId, strategy);
    }

    @Getter
    @Setter
    private class Result {
        private boolean isOk = true;
    }
}
