package com.ioc;

import java.util.concurrent.Future;

/**
 * @author wanchongyang
 * @date 2018/7/31 下午6:00
 */
public interface DepartmentService {
    void print();

    Object getName();

    Future<String> getTitle();
}
