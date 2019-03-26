package ru.xegex.strategy.starter.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * This ...
 * <p></p>
 *
 * @author Dmitriy Erokhin <a href="mailto:de@ineb.ru">de@ineb.ru</a>
 **/
@Getter
@Setter
public class Security {
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Security security = (Security) o;
        return Objects.equals(code, security.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
