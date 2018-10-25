package com.java8;


import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class MapConverter<K, V> {

    private final Map<K, V> map;

    private List<Predicate<? super V>> lazyValueFilters = Collections.emptyList();

    private static final MapConverter<?, ?> EMPTY = new MapConverter<>(Collections.emptyMap());

    private MapConverter(Map<K, V> map) {
        this.map = map;
    }

    public MapConverter<K, V> filter(Predicate<? super V> valueFilter) {
        Objects.requireNonNull(valueFilter, "valueFilter cannot be null");
        if (map.isEmpty()) {
            return this;
        }
        if (lazyValueFilters.isEmpty()) {
            lazyValueFilters = new ArrayList<>();
        }
        lazyValueFilters.add(valueFilter);
        return this;
    }

    public <U> MapConverter<K, U> convert(Function<? super V, ? extends U> mapper) {
        Objects.requireNonNull(mapper, "mapper cannot be null");

        if (get().isEmpty()) {
            return empty();
        }

        Map<K, U> newMap = new HashMap<>(get().size(), 1);
        get().forEach((k, v) -> newMap.put(k, mapper.apply(v)));
        return of(newMap);
    }

    public Map<K, V> get() {
        doFilters();
        return map;
    }

    private void doFilters() {
        if (lazyValueFilters.isEmpty() || map.isEmpty()) {
            return;
        }
        Iterator<V> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            V v = iterator.next();
            for (Predicate<? super V> predicate : lazyValueFilters) {
                if (predicate.test(v)) {
                    iterator.remove();
                    break;
                }
            }
        }
        lazyValueFilters = Collections.emptyList();
    }

    public static <K, V> MapConverter<K, V> empty() {
        @SuppressWarnings("unchecked")
        MapConverter<K, V> empty = (MapConverter<K, V>) EMPTY;
        return empty;
    }

    public static <K, V> MapConverter<K, V> of(Map<K, V> map) {
        Objects.requireNonNull(map, "map cannot be null");
        return new MapConverter<>(map);
    }

    public <NK, NV> MapConverter<NK, NV> convert(Function<? super K, ? extends NK> keyMapper, Function<? super V, ? extends NV> valueMapper) {
        Objects.requireNonNull(keyMapper, "keyMapper cannot be null");
        Objects.requireNonNull(valueMapper, "valueMapper cannot be null");

        if (get().isEmpty()) {
            return empty();
        }

        Map<NK, NV> newMap = new HashMap<>(get().size(), 1);
        get().forEach((k, v) -> newMap.put(keyMapper.apply(k), valueMapper.apply(v)));
        return of(newMap);
    }
}
