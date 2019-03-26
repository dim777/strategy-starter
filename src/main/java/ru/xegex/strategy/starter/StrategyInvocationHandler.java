package ru.xegex.strategy.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.xegex.strategy.starter.model.Security;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This ...
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
public class StrategyInvocationHandler implements InvocationHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(StrategyInvocationHandler.class);
    private static final String SECURITIES_METHOD = "securities";
    private static final String CODE_METHOD = "code";

    private final Map<String, Method> methods = new HashMap<>();
    private Object target;
    private String strategyName;
    private Security[] securities;

    public StrategyInvocationHandler(Object target, String strategyName, Security[] securities) {
        this.target = target;
        this.strategyName = strategyName;
        this.securities = securities;

        for (Method method : target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (methodName.equals(SECURITIES_METHOD)) {
            return Arrays.asList(securities);
        }
        if (methodName.equals(CODE_METHOD)) {
            return strategyName;
        }
        return methods.get(method.getName()).invoke(target, args);
    }
}
