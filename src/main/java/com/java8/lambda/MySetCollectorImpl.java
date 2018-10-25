package com.java8.lambda;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自定义实现Collector
 * @author wanchongyang
 * @date 2018/6/27 下午2:11
 */
public class MySetCollectorImpl<T> implements Collector<T, Set<T>, Map<T, T>> {

    private List<Set<T>> setList = new ArrayList<>();
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked...");
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked...");
        // return (set, item) -> {
        //     set.add(item);
        //     setList.add(set);
        //     System.out.println(Thread.currentThread().getName() + ": " + item + ", is the same address:" + isSameAddress(setList));
        // };

        return (set, item) -> {
            set.add(item);
            setList.add(set);
            System.out.println(Thread.currentThread().getName() + ": " + item + ", current set content is :" +  set + ". is the same set? " + isSameAddress(setList));
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked...");
        return (set1, set2) -> {
            set1.addAll(set2);
            System.out.println("really to combine... ");
            return set1;
        };
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoked...");
        return (set) -> {
            Map<T, T> map = new HashMap<>(16);
            set.forEach(item -> map.put(item, item));
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        // 1、IDENTITY_FINISH
        //
        // a. 当Set<Characteristics>中包含了IDENTITY_FINISH枚举值时，finisher 函数就不会执行了。
        // 因为一旦有这个值，程序就会认为可变的结果容器结果就是最终结果，没有必要再去转换。
        // b. Set中包含了IDENTITY_FINISH枚举值时，我们自己就需要保证中间结果类型就是最终的结果类型，否则就会强制类型转化失败，
        // 代码会抛出ClassCastException异常。ReferencePipeline类的collect方法的最后一个return语句佐证。
        // return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)
        //         ? (R) container
        //         : collector.finisher().apply(container);


        // 2、CONCURRENT
        //
        // a. 当Set<Characteristics>中包含了CONCURRENT时，意味着程序就会默认多线程(前提是得到并行流)可以并行调用accumulator函数并且最终能返回正确的结果。
        // b. 当Set<Characteristics>中包含了CONCURRENT时，多线程(前提是得到并行流)其实操作的是同一个结果容器，
        // 这个时候就需要开发者自己保证多线程操作同一个结果容器的准确性。
        // c. 当Set<Characteristics>中包含了CONCURRENT时，尽管是并行流，combiner方法返回的函数式接口实例不会得到调用，
        // 这是因为操作的是同一个结果容器，没必要执行。
        // d. 由于b，当使用并行流时，不要在累加器返回的函数式接口实例中做额外的操作，比如打印(迭代)set内容，否则可能会抛出ConcurrentModificationException
        // (it is not generally permissible for one thread to modify a Collection while another thread is iterating over it)。
        // e. 当Set<Characteristics>中**不包含**CONCURRENT时, 并行流在调用collect方法时操作的是多个不同的结果容器，
        // 并且一定会执行combiner方法返回的函数式接口实例。

        // 3、UNORDERED

        // a. 当认为数据源是无序的，比如Set，就可以添加这个特性，否则不应该添加该枚举值。因为该特性不承诺保存的顺序和元素出现的顺序一致。

        // return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED, Characteristics.CONCURRENT));
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
        // return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    /**
     * 测试每次的Set是否为同一个对象
     *
     * @param list
     * @return
     */
    private boolean isSameAddress(List<Set<T>> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) != list.get(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world", "a", "b", "c");
        Map<String, String> result = list.parallelStream().collect(new MySetCollectorImpl<>());
        System.out.println(result);
    }
}
