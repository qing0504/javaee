package com.pattern.builder;

/**
 * 抽象建造者类
 * 抽象建造者（Builder）角色：给 出一个抽象接口，以规范产品对象的各个组成成分的建造。
 * Created by wanchongyang on 2017/10/11.
 */
public interface Builder {
    void buildPart1();
    void buildPart2();
    Product retrieveResult();
}
