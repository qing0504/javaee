package com.reflect;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author wanchongyang
 * @date 2018/9/6 下午2:56
 */
public class TypeTokenTest {
    public static void main(String[] args) {
        // 获取一个基本的、原始类的TypeToken
        TypeToken<String> stringTypeToken = TypeToken.of(String.class);
        TypeToken<Integer> integerTypeToken = TypeToken.of(Integer.class);
        System.out.println("stringTypeToken:" + stringTypeToken);
        System.out.println("integerTypeToken:" + integerTypeToken);
        System.out.println("==================================");
        // 获得一个含有泛型的类型的TypeToken
        TypeToken<List<String>> listTypeToken = new TypeToken<List<String>>() {};
        System.out.println("listTypeToken:" + listTypeToken);

        TypeToken<Map<String, BigInteger>> mapToken = mapToken(
                TypeToken.of(String.class),
                TypeToken.of(BigInteger.class)
        );
        TypeToken<Map<Integer, Queue<String>>> complexToken = mapToken(
                TypeToken.of(Integer.class),
                new TypeToken<Queue<String>>() {}
        );
        System.out.println("mapToken:" + mapToken);
        System.out.println("complexToken:" + complexToken);
    }

    static <K, V> TypeToken<Map<K, V>> mapToken(TypeToken<K> keyToken, TypeToken<V> valueToken) {
        return new TypeToken<Map<K, V>>() {}
                .where(new TypeParameter<K>() {}, keyToken)
                .where(new TypeParameter<V>() {}, valueToken);
    }
}
