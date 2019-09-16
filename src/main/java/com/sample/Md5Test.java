package com.sample;

import cn.hutool.core.codec.Base64Encoder;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author wanchongyang
 * @date 2019-08-13 23:33
 */
public class Md5Test {
    public static void main(String[] args) {
        String jsonStr = "{\"cardOrderNo\":\"N1201908130000007043\",\"cardSampleId\":359,\"deliveredCount\":0,\"deliveryBatchId\":41,\"deliveryType\":\"normal\",\"enterpriseId\":22462,\"queryCardRequest\":{\"cardSampleId\":359,\"page\":1,\"pageSize\":50,\"productCategory\":13,\"saleOrderId\":34354},\"subDeliveryTaskId\":33,\"userIdList\":[\"ChenTao\"]}";
        System.out.println(Base64Encoder.encode(jsonStr));
        System.out.println(DigestUtils.md5Hex(jsonStr));

        int a = 1000000;
        Integer b = 1000000;
        System.out.println(a == b);
        System.out.println(a != b);
    }
}
