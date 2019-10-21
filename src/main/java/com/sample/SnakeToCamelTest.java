package com.sample;

import com.alibaba.fastjson.JSON;
import com.common.utils.StringHelpers;
import com.sample.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanchongyang
 * @date 2019/9/20 4:13 下午
 */
public class SnakeToCamelTest {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>(64);
        map.put("user_id", 1);
        map.put("user_name", "张三");
        String jsonStr = JSON.toJSONString(map);

        System.out.println(jsonStr);
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println(user);

        System.out.println(StringHelpers.underLineToCamel("user_id"));
        System.out.println(StringHelpers.underlineToHump("user_name"));
    }
}
