package com.lb.impl;

import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.StrUtil;
import com.lb.BaseLoadBalance;
import com.lb.LoadBalance;
import com.lb.Node;

import java.util.List;

/**
 * @author wanchongyang
 * @date 2022/3/3 1:49 PM
 */
public class IpHashLoadBalance<N extends Node> extends BaseLoadBalance<N> implements LoadBalance<N> {

    @Override
    protected N doSelect(List<N> nodes, String ip) {
        if (StrUtil.isBlank(ip)) {
            ip = "127.0.0.1";
        }

        int length = nodes.size();
        int index = hash(ip) % length;
        return nodes.get(index);
    }

    public int hash(String text) {
        return HashUtil.fnvHash(text);
    }

}
