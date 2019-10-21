package com.sample;

import com.common.utils.DateUtil;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author wanchongyang
 * @date 2017/10/15
 */
public class DateTest {
    public static void main(String[] args) {
        DateFormat dateFormat = DateUtil.getDateFormat();
        String dateStr = dateFormat.format(new Date());
        System.out.println(dateStr);

        BigDecimal bd = new BigDecimal("1.5094656E+12");
        Date date = new Date(bd.longValue());
        System.out.println(DateUtil.formatDateFull(date));

        bd = new BigDecimal("1.5079968E+12");
        System.out.println(DateUtil.formatDateFull(new Date(bd.longValue())));

        int expireSeconds = (int) TimeUnit.HOURS.toSeconds(2);
        System.out.println(expireSeconds);

        Date longToDate = new Date(1528250400007L);
        System.out.println(DateUtil.formatDateFull(longToDate));

        System.out.println(DateFormatUtils.format(new Date(), "yyyMMdd"));
        // System.out.println(DateFormatUtils.format(new Date(), "yyyyMMdd"));
    }
}
