package com.lb.impl;

import com.lb.BaseLoadBalance;
import com.lb.LoadBalance;
import com.lb.Node;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wanchongyang
 * @date 2022/3/2 5:58 PM
 */
public class WeightRandomLoadBalance<N extends Node> extends BaseLoadBalance<N> implements LoadBalance<N> {

    private final Random random = ThreadLocalRandom.current();

    @Override
    protected N doSelect(List<N> nodes, String ip) {

        int length = nodes.size();
        AtomicInteger totalWeight = new AtomicInteger(0);
        for (N node : nodes) {
            Integer weight = node.getWeight();
            totalWeight.getAndAdd(weight);
        }

        if (totalWeight.get() > 0) {
            int offset = random.nextInt(totalWeight.get());
            for (N node : nodes) {
                // 让随机值 offset 减去权重值
                offset -= node.getWeight();
                if (offset < 0) {
                    // 返回相应的 Node
                    return node;
                }
            }
        }

        // 直接随机返回一个
        return nodes.get(random.nextInt(length));
    }

}
