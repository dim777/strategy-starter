package ru.xegex.strategy.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * This ...
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
@Configuration
@SpringBootApplication
@EnableStrategyContainer
public class SpringCtxTestsConfig {

    public static void main(String[] args) {
        SpringApplication.run(SpringCtxTestsConfig.class);
    }
}
