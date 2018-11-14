package com.validate.validate;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证上下文对象，包含原始数据集合、统计数据、配置元数据
 * @author wanchongyang
 * @date 2018/11/9 11:19 AM
 */
@Data
public class ValidateContext {
    /**
     * 处理数据集合
     */
    private List<ValidateItem> itemList;
    /**
     * 总条数
     */
    private int totalCnt;
    /**
     * 成功总条数
     */
    private int successCnt;
    /**
     * 失败总条数
     */
    private int failCnt;
    /**
     * 配置元数据，表头信息
     */
    private MetadataTitleFields titleFields;

    public ValidateContext() {
        itemList = new ArrayList<>();
    }
}
