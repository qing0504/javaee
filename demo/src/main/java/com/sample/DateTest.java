package com.sample;

import com.common.utils.DateUtil;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author wanchongyang
 * @date 2017/10/15
 */
public class DateTest {
    public static void main(String[] args) {
        DateFormat dateFormat = DateUtil.getDateFormat();
        String dateStr = dateFormat.format(new Date());
        System.out.println(dateStr);
    }
}
