package ru.xegex.strategy.starter.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * This ...
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
@Getter
@Setter
public class Order {
    private UUID id;
    private Security security;
    private OrderType orderType;
    private Integer volume;

    enum OrderType {
        BUY(0),
        SELL(1),
        STOP_LIMIT(2);

        OrderType(Integer code) {
            this.code = code;
        }

        Integer code;
    }
}
