package com.lb.impl;

import com.lb.BaseLoadBalance;
import com.lb.LoadBalance;
import com.lb.Node;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author wanchongyang
 * @date 2022/3/3 1:51 PM
 */
public class ConsistentHashLoadBalance<N extends Node> extends BaseLoadBalance<N> implements LoadBalance<N> {

    private final ConcurrentMap<String, ConsistentHashSelector<?>> selectors = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    protected N doSelect(List<N> nodes, String ip) {
        // 分片数，这里设为节点数的 4 倍
        Integer replicaNum = nodes.size() * 4;
        // 获取 nodes 原始的 hashcode
        int identityHashCode = System.identityHashCode(nodes);

        // 如果 nodes 是一个新的 List 对象，意味着节点数量发生了变化
        // 此时 selector.identityHashCode != identityHashCode 条件成立
        ConsistentHashSelector<N> selector = (ConsistentHashSelector<N>) selectors.get(ip);
        if (selector == null || selector.identityHashCode != identityHashCode) {
            // 创建新的 ConsistentHashSelector
            selectors.put(ip, new ConsistentHashSelector<>(nodes, identityHashCode, replicaNum));
            selector = (ConsistentHashSelector<N>) selectors.get(ip);
        }
        // 调用 ConsistentHashSelector 的 select 方法选择 Node
        return selector.select(ip);
    }

    /**
     * 一致性哈希选择器
     */
    private static final class ConsistentHashSelector<N extends Node> {

        /**
         * 存储虚拟节点
         */
        private final TreeMap<Long, N> virtualNodes;

        private final int identityHashCode;

        /**
         * 构造器
         *
         * @param nodes            节点列表
         * @param identityHashCode hashcode
         * @param replicaNum       分片数
         */
        ConsistentHashSelector(List<N> nodes, int identityHashCode, Integer replicaNum) {
            this.virtualNodes = new TreeMap<>();
            this.identityHashCode = identityHashCode;
            // 获取虚拟节点数，默认为 100
            if (replicaNum == null) {
                replicaNum = 100;
            }
            for (N node : nodes) {
                for (int i = 0; i < replicaNum / 4; i++) {
                    // 对 url 进行 md5 运算，得到一个长度为16的字节数组
                    byte[] digest = md5(node.getUrl());
                    // 对 digest 部分字节进行 4 次 hash 运算，得到四个不同的 long 型正整数
                    for (int j = 0; j < 4; j++) {
                        // h = 0 时，取 digest 中下标为 0 ~ 3 的4个字节进行位运算
                        // h = 1 时，取 digest 中下标为 4 ~ 7 的4个字节进行位运算
                        // h = 2, h = 3 时过程同上
                        long m = hash(digest, j);
                        // 将 hash 到 node 的映射关系存储到 virtualNodes 中，
                        // virtualNodes 需要提供高效的查询操作，因此选用 TreeMap 作为存储结构
                        virtualNodes.put(m, node);
                    }
                }
            }
        }

        public N select(String key) {
            // 对参数 key 进行 md5 运算
            byte[] digest = md5(key);
            // 取 digest 数组的前四个字节进行 hash 运算，再将 hash 值传给 selectForKey 方法，
            // 寻找合适的 Node
            return selectForKey(hash(digest, 0));
        }

        private N selectForKey(long hash) {
            // 查找第一个大于或等于当前 hash 的节点
            Map.Entry<Long, N> entry = virtualNodes.ceilingEntry(hash);
            // 如果 hash 大于 Node 在哈希环上最大的位置，此时 entry = null，
            // 需要将 TreeMap 的头节点赋值给 entry
            if (entry == null) {
                entry = virtualNodes.firstEntry();
            }
            // 返回 Node
            return entry.getValue();
        }

    }

    /**
     * 计算 hash 值
     */
    public static long hash(byte[] digest, int number) {
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL;
    }

    /**
     * 计算 MD5 值
     */
    public static byte[] md5(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.reset();
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        md5.update(bytes);
        return md5.digest();
    }

}
