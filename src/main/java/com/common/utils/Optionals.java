package com.common.utils;

import com.google.common.base.Supplier;

import java.util.Objects;
import java.util.Optional;

/**
 * @see Optional
 * <p>
 * 补充 Optional 中缺失的方法
 */
public class Optionals<T> {

    private final Optional<T> value;

    private Optionals(Optional<T> optional) {
        value = optional;
    }

    public Optionals<T> or(Supplier<Optional<T>> supplier) {
        Objects.requireNonNull(supplier);
        if (value.isPresent()) {
            return this;
        }
        return of(supplier.get());
    }

    public static <T> Optionals<T> of(Optional<T> optional) {
        Objects.requireNonNull(optional);
        return new Optionals<>(optional);
    }

    public Optional<T> get() {
        return value;
    }
}
