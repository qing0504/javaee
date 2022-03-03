package com.lb.impl;

import com.lb.BaseLoadBalance;
import com.lb.Node;

import java.util.List;
import java.util.Random;

/**
 * @author wanchongyang
 * @date 2022/3/2 5:56 PM
 */
public class RandomLoadBalance<N extends Node> extends BaseLoadBalance<N> {
    private final Random random = new Random();

    @Override
    protected N doSelect(List<N> nodes, String ip) {
        // 在列表中随机选取一个节点
        int index = random.nextInt(nodes.size());
        return nodes.get(index);
    }
}
