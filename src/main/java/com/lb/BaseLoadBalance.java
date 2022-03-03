package com.lb;

import cn.hutool.core.collection.CollectionUtil;

import java.util.List;

/**
 * @author wanchongyang
 * @date 2022/3/2 5:55 PM
 */
public abstract class BaseLoadBalance<N extends Node> implements LoadBalance<N> {
    @Override
    public N select(List<N> nodes, String ip) {
        if (CollectionUtil.isEmpty(nodes)) {
            return null;
        }

        // 如果 nodes 列表中仅有一个 node，直接返回即可，无需进行负载均衡
        if (nodes.size() == 1) {
            return nodes.get(0);
        }

        return doSelect(nodes, ip);
    }

    protected abstract N doSelect(List<N> nodes, String ip);

}
