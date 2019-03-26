package ru.xegex.strategy.starter;

import io.github.classgraph.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;
import ru.xegex.strategy.starter.model.Security;
import ru.xegex.strategy.starter.model.Tick;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * This ...
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
@Service
public class StrategyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof StrategyContainer) {
            //todo: should implement quote type in strategy def
            StrategyContainer<Tick> strategyContainer = (StrategyContainer<Tick>) bean;

            try (ScanResult scanResult = new ClassGraph().enableAnnotationInfo().scan()) {
                for (ClassInfo clazzInfo : scanResult.getClassesWithAnnotation(StrategyDefinition.class.getCanonicalName())) {
                    AnnotationInfo strategyDefinitionInfo = clazzInfo.getAnnotationInfo(StrategyDefinition.class.getCanonicalName());

                    String strategyName = null;
                    Security[] securities = null;

                    for (AnnotationParameterValue d : strategyDefinitionInfo.getParameterValues()) {
                        if (d.getName().equals(StrategyDefinition.STRATEGY_NAME_FIELD)) {
                            strategyName = (String) d.getValue();
                            if (strategyName.equals(StrategyDefinition.STRATEGY_NAME_DEFAULT_VALUE)) {
                                strategyName = clazzInfo.getName();
                            }
                        } else if (d.getName().equals(StrategyDefinition.STRATEGY_SECURITIES_FIELD)) {
                            String[] ss = (String[]) d.getValue();
                            int length = ss.length;
                            securities = new Security[length];
                            for (int i = 0; i < length; i++) {
                                Security security = new Security();
                                security.setCode(ss[i]);
                                securities[i] = security;
                            }
                        }
                    }

                    String clazzInfoName = clazzInfo.getName();
                    Strategy<Tick> strategy = getTickStrategy(clazzInfoName, strategyName, securities);
                    strategyContainer.subscribe(strategy);
                }
            }
        }
        return bean;
    }

    @SuppressWarnings("unchecked")
    private Strategy<Tick> getTickStrategy(String clazzInfoName, String strategyName, Security[] securities) {
        try {
            Class<?> strategyClazz = Class.forName(clazzInfoName);
            Constructor<?> declaredConstructor = strategyClazz.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            Object s = declaredConstructor.newInstance();
            return (Strategy<Tick>) Proxy.newProxyInstance(
                    StrategyBeanPostProcessor.class.getClassLoader(),
                    new Class[]{Strategy.class},
                    new StrategyInvocationHandler(s, strategyName, securities));
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | IllegalAccessException
                | InstantiationException
                | InvocationTargetException e) {
            throw new StrategyEx("Couldn't initialize strategy, ex=", e);
        }
    }
}
