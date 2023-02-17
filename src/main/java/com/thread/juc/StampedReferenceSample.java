package com.thread.juc;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author wanchongyang
 * @date 2022/5/15 23:53
 */
public class StampedReferenceSample {
    public static void main(String[] args) {
        String initialRef   = "initial value referenced";
        int    initialStamp = 0;

        AtomicStampedReference<String> atomicStringReference =
                new AtomicStampedReference<String>(
                        initialRef, initialStamp
                );

        String newRef   = "new value referenced";
        int    newStamp = initialStamp + 1;

        boolean exchanged = atomicStringReference
                .compareAndSet(
                        initialRef, newRef,
                        initialStamp, newStamp);
        System.out.println("exchanged: " + exchanged);  //true

        exchanged = atomicStringReference
                .compareAndSet(
                        initialRef, "new string",
                        newStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged);  //false

        exchanged = atomicStringReference
                .compareAndSet(
                        newRef, "new string",
                        initialStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged);  //false

        exchanged = atomicStringReference
                .compareAndSet(
                        newRef, "new string",
                        newStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged);  //true
    }
}
