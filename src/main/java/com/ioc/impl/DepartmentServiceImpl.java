package com.ioc.impl;

import com.ioc.DepartmentService;
import com.ioc.support.Component;
import com.thread.executor.support.Async;
import com.thread.executor.support.AsyncResult;

import java.util.concurrent.Future;

/**
 * @author wanchongyang
 * @date 2018/7/31 下午6:02
 */
@Component("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Async
    @Override
    public void print() {
        System.out.println(Thread.currentThread().getName() + " print() async execution.");
    }

    @Async("fixedTaskExecutor")
    @Override
    public String getName() {
        System.out.println(Thread.currentThread().getName() + " getName() async execution.");
        return "my name is development.";
    }

    @Async("fixedTaskExecutor")
    @Override
    public Future<String> getTitle() {
        System.out.println(Thread.currentThread().getName() + " getTitle() async execution.");
        String obj = "my title is GAT.";
        return new AsyncResult<>(obj);
    }

    @Async("fixedTaskExecutor")
    @Override
    public int calculate(int a, int b) {
        System.out.println(Thread.currentThread().getName() + " calculate() async execution.");
        int c = 1/0;
        return a*b;
    }
}
