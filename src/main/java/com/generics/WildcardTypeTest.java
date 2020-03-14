package com.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * WildcardType.getUpperBounds() 获取泛型类型的上边界
 * WildcardType.getLowerBounds() 获取泛型类型的下边界
 * @author wanchongyang
 * @date 2020/3/14 10:57 下午
 */
public class WildcardTypeTest {
    public static void main(String[] args) throws NoSuchFieldException {
        Type type = User.class.getDeclaredField("list1").getGenericType();
        System.out.println(type instanceof ParameterizedType);      // true
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type actualType = parameterizedType.getActualTypeArguments()[0];     // ? extends Number
        WildcardType wildcardType = (WildcardType) actualType;
        System.out.println((wildcardType.getUpperBounds()[0]) == Number.class);  // true

        type = User.class.getDeclaredField("list2").getGenericType();
        wildcardType = (WildcardType) ((ParameterizedType) type).getActualTypeArguments()[0];
        System.out.println(wildcardType.getLowerBounds()[0] == Integer.class);   // true
    }
}

class User<T> {
    List<? extends Number> list1;
    List<? super Integer> list2;
}
