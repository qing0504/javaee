package com.lb;

import java.util.List;

/**
 * @author wanchongyang
 * @date 2022/3/2 5:54 PM
 */
public interface LoadBalance<N extends Node> {
    N select(List<N> nodes, String ip);
}
