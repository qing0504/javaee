package com.common.utils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 转换 List
 */
public class ListConverter<T> implements CollectionConverter<T> {

    private final List<T> list;

    private List<Predicate<? super T>> lazyFilters = Collections.emptyList();

    private static final ListConverter<?> EMPTY = new ListConverter<>(Collections.emptyList());

    private ListConverter(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public ListConverter<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate cannot be null");

        if (list.isEmpty()) {
            return this;
        }
        if (lazyFilters.isEmpty()) {
            lazyFilters = new ArrayList<>();
        }
        lazyFilters.add(predicate);
        return this;
    }

    @Override
    public List<T> get() {
        doFilters();
        return list;
    }

    @Override
    public <U> ListConverter<U> convert(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper, "mapper cannot be null");
        if (get().isEmpty()) {
            return empty();
        }

        List<U> newList = new ArrayList<>(get().size());
        get().forEach(t -> newList.add(mapper.apply(t)));
        return of(newList);
    }

    private void doFilters() {
        if (lazyFilters.isEmpty() || list.isEmpty()) {
            return;
        }
        final Iterator<T> each = list.iterator();
        while (each.hasNext()) {
            T t = each.next();
            for (Predicate<? super T> predicate : lazyFilters) {
                if (predicate.test(t)) {
                    each.remove();
                    break;
                }
            }
        }
        lazyFilters = Collections.emptyList();
    }

    public static <T> ListConverter<T> empty() {
        @SuppressWarnings("unchecked")
        ListConverter<T> empty = (ListConverter<T>) EMPTY;
        return empty;
    }

    public static <T> ListConverter<T> of(List<T> list) {
        Objects.requireNonNull(list, "map cannot be null");
        return new ListConverter<>(list);
    }

    @Override
    public <K, V> MapConverter<K, V> convert(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {
        Objects.requireNonNull(keyMapper, "keyMapper cannot be null");
        Objects.requireNonNull(valueMapper, "valueMapper cannot be null");

        if (get().isEmpty()) {
            return MapConverter.empty();
        }

        Map<K, V> map = new HashMap<>(get().size(), 1);
        for (T t : get()) {
            map.put(keyMapper.apply(t), valueMapper.apply(t));
        }
        return MapConverter.of(map);
    }

    public Set<T> toSet() {
        return new HashSet<>(get());
    }

    public <K, V> MapConverter<K, List<V>> toListMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {
        Objects.requireNonNull(keyMapper, "keyMapper cannot be null");
        Objects.requireNonNull(valueMapper, "valueMapper cannot be null");

        if (get().isEmpty()) {
            return MapConverter.empty();
        }
        Map<K, List<V>> map = new HashMap<>(get().size(), 1);
        for (T t : get()) {
            K key = keyMapper.apply(t);
            List<V> vList = map.get(key);
            if (vList == null) {
                vList = new ArrayList<>();
            }
            vList.add(valueMapper.apply(t));
            map.putIfAbsent(key, vList);
        }
        return MapConverter.of(map);
    }
}
