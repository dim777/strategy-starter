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
public class MarketInfo {
    private UUID id;
    private Security security;
    private Double spread;
}
