package com.java8;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CollectionConverter<E> {

    CollectionConverter<E> filter(Predicate<? super E> predicate);

    Collection<E> get();

    <U> CollectionConverter<U> convert(Function<? super E, ? extends U> mapper);

    <K, V> MapConverter<K, V> convert(Function<? super E, ? extends K> keyMapper, Function<? super E, ? extends V> valueMapper);
}
