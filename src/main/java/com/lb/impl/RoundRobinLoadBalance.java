package com.lb.impl;

import com.lb.BaseLoadBalance;
import com.lb.LoadBalance;
import com.lb.Node;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wanchongyang
 * @date 2022/3/2 6:04 PM
 */
public class RoundRobinLoadBalance<N extends Node> extends BaseLoadBalance<N> implements LoadBalance<N> {

    private final AtomicInteger position = new AtomicInteger(0);

    @Override
    protected N doSelect(List<N> nodes, String ip) {
        int length = nodes.size();
        // 如果位置值已经等于节点数，重置为 0
        position.compareAndSet(length, 0);
        N node = nodes.get(position.get());
        position.getAndIncrement();
        return node;
    }

}
