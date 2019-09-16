package com.sample;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanchongyang
 * @date 2019-07-30 15:44
 */
public class UUIDTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int len = 3;
        for (int i = 0; i < len; i++) {
            list.add(IdUtil.fastSimpleUUID());
        }

        System.out.println(JSON.toJSONString(list));

        int index = 1;

        // System.out.println((index++) * 2);
        // System.out.println(index);
        //
        // System.out.println((++index) * 2);
        // System.out.println(index);

        do {
            System.out.println(index);
        } while (index++ * 2 < 3);
    }
}
