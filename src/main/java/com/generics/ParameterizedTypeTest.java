package com.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * // 获取最外层`<>`前的类型
 * // Map<Integer,Map<String,List<Integer>>> Map.class
 * ParameterizedType.getRawType();
 *
 * // 获取最外层'<>'里面的类型
 * // Map<Integer,Map<String,List<Integer>>> 得到一个数组array
 *
 * // array[0] Type可以转化成Integer.class
 * // array[1] Map<String,List<Integer>>,Type属于ParameterizedType
 * ParameterizedType.getActualTypeArguments();
 *
 * @author wanchongyang
 * @date 2020/3/14 10:41 下午
 */
public class ParameterizedTypeTest {
    public static void main(String[] args) throws NoSuchFieldException {
        Type type = Student.class.getDeclaredField("f1").getGenericType();
        Class clazz = (Class) type;
        System.out.println(clazz == Friend.class); // true

        Type type2 = Student.class.getDeclaredField("f2").getGenericType();
        ParameterizedType parameterizedType = (ParameterizedType) type2;
        Class clazz2 = (Class) parameterizedType.getRawType();
        System.out.println(clazz2 == Friend.class);  // true

        Type[] actualTypes = parameterizedType.getActualTypeArguments();
        clazz = (Class) actualTypes[0];
        System.out.println(clazz == Integer.class); // true
    }
}

class Friend<T> {

}

class Student {

    Friend f1;

    Friend<Integer> f2;

}