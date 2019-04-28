package com.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 泛型示例，Collections.copy()
 *
 * @author wanchongyang
 * @date 2019-02-21 17:38
 * @see java.util.Collections
 */
public class GenericsTest {
    public static void main(String[] args) {
        // PECS:Producer Extends,Consumer Super
        // extends不能add只能get
        List<? extends Number> listNumber = new ArrayList<Number>(16);
        List<? extends Number> listInteger = new ArrayList<Integer>(16);
        List<? extends Number> listDouble = new ArrayList<Double>(16);

        // super不能get只能add
        List<? super Integer> integerList = new ArrayList<Integer>(16);
        List<? super Integer> numberList = new ArrayList<Number>(16);
        integerList.add(1);

        Collections.copy(new ArrayList<Integer>(), new ArrayList<Integer>());
        Collections.copy(new ArrayList<Number>(), new ArrayList<Integer>());

        // create two lists
        List<String> srclst = new ArrayList<String>(5);
        List<String> destlst = new ArrayList<String>(10);

        // populate two lists
        srclst.add("Java");
        srclst.add("is");
        srclst.add("best");

        destlst.add("C++");
        destlst.add("is");
        destlst.add("older");
        destlst.add("older");

        System.out.println("Before Value of source list: " + srclst);
        System.out.println("Before Value of destination list: " + destlst);
        // copy into dest list
        Collections.copy(destlst, srclst);

        System.out.println("After Value of source list: " + srclst);
        System.out.println("After Value of destination list: " + destlst);

        // result:
        // Before Value of source list: [Java, is, best]
        // Before Value of destination list: [C++, is, older, older]
        // After Value of source list: [Java, is, best]
        // After Value of destination list: [Java, is, best, older]
    }
}
