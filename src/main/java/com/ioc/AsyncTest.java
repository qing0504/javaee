package com.ioc;

import com.common.utils.ConcurrentUtils;
import com.ioc.support.ApplicationContext;
import com.ioc.support.DefaultApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wanchongyang
 * @date 2018/7/31 下午6:00
 */
public class AsyncTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new DefaultApplicationContext(new String[]{"com.ioc", "com.thread.executor"});
        DepartmentService departmentService = (DepartmentService) applicationContext.getBean("departmentService");
        System.out.println("==============print==================");
        departmentService.print();
        departmentService.print("Hello World.");
        ConcurrentUtils.sleep(1);
        System.out.println("================getName================");
        String name = departmentService.getName();
        System.out.println(name);
        ConcurrentUtils.sleep(1);
        System.out.println("================getTitle================");
        Future<String> future = departmentService.getTitle();
        while (true) {
            if (future.isDone()) {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        System.out.println("================calculate================");
        departmentService.calculate(3, 9);
        ConcurrentUtils.sleep(1);
        System.out.println("================================");
        System.out.println("it is over.");
    }
}
