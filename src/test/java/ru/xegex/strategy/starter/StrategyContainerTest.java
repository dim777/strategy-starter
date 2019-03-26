package ru.xegex.strategy.starter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;
import ru.xegex.strategy.starter.model.Security;
import ru.xegex.strategy.starter.model.Tick;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyContainerTest {
    @Autowired
    private StrategyContainer<Tick> strategyContainer;

    @Test
    public void testInjectedBean() throws Exception {
        Security security = new Security();
        security.setCode("LKOH");

        Tick tick = new Tick();
        tick.setId(UUID.fromString("0a022d1e-9dc5-465a-b642-f974f9dc6240"));
        tick.setSecurity(security);

        boolean submit = strategyContainer.submit(tick);
        assertTrue(submit);
        Strategy<Tick> strategy = strategyContainer.strategies().stream()
                .filter(s -> s.code().equals("TestStrategy"))
                .findFirst()
                .orElseThrow(() -> new StrategyEx("Couldn't find strategy"));

        Class<StrategyInvocationHandler> clazz = StrategyInvocationHandler.class;
        Field field = clazz.getDeclaredField("target");
        field.setAccessible(true);
        StrategyInvocationHandler invocationHandler = (StrategyInvocationHandler) Proxy.getInvocationHandler(strategy);
        TestStrategy1 s = (TestStrategy1) ReflectionUtils.getField(field, invocationHandler);

        Map<Integer, Tick> ticks = s.getTicks();
        Tick expectedTick = ticks.get(0);
        assertEquals(expectedTick, tick);
    }
}