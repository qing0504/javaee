package com.generics;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * GenericArrayType.getGenericComponentType()
 * 得到数组的泛型类，多维数组去掉最右边的第一层[]
 * @author wanchongyang
 * @date 2020/3/14 10:45 下午
 */
public class GenericArrayTypeTest {
    public static void main(String[] args) throws NoSuchFieldException {
        Type type = Company.class.getDeclaredField("array1").getGenericType();
        GenericArrayType genericArrayType = (GenericArrayType) type;    // Friend<Integer>[]
        type = genericArrayType.getGenericComponentType(); //Friend<Integer>,使用ParameterizedType提取下一步信息
        System.out.println(((ParameterizedType) type).getActualTypeArguments()[0] == Integer.class); // true

        type = Company.class.getDeclaredField("array2").getGenericType();
        System.out.println(((GenericArrayType) type).getGenericComponentType()); // Friend<Integer>[]

        type = Company.class.getDeclaredField("array3").getGenericType();
        System.out.println(type instanceof Class);              // true
        System.out.println(((Class)type) == Friend[].class);    // true

        type = Company.class.getDeclaredField("array4").getGenericType();
        System.out.println(type instanceof Class);              // true
        System.out.println(((Class)type) == Friend[][].class);    // true

    }
}

class Company {
    Friend<Integer>[] array1;
    Friend<Integer>[][] array2;

    Friend[] array3;
    Friend[][] array4;
}

