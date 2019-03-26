package ru.xegex.strategy.starter;


import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Objects;

/**
 * This ...
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
public class StrategyContainerConfigurationSelector implements ImportSelector {
    private static final String VALUE = "value";
    private static final String DEFAULT = "default";
    private static final String BEAN_CONFIGURATION_DEFAULT = DefaultConfiguration.class.getPackageName() + "." + DefaultConfiguration.class.getSimpleName();

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes
                (EnableStrategyContainer.class.getName(), false));
        String criteria = Objects.requireNonNull(attributes).getString(VALUE);
        if (criteria.equals(DEFAULT)) {
            return new String[]{
                    BEAN_CONFIGURATION_DEFAULT
            };
        } else {
            // not implemented yet
            return new String[]{};
        }
    }

}
