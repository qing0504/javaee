package com.sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wanchongyang
 * @date 2020/3/15 9:46 下午
 */
public class RegexTest {
    // private static Pattern pattern = Pattern.compile("^(https?)://([a-zA-Z0-9_-]\\.services\\.dev\\.ofc)[^\\s]*");
    // private static Pattern pattern = Pattern.compile("^(https?)://(\\w|-)+(\\.services\\.dev\\.ofc)[^\\s]*");
    // private static Pattern pattern = Pattern.compile("^(http://)(\\w|-)+(\\.services\\.dev\\.ofc)");
    private static final String serviceNameSuffix = ".services.dev.ofc";
    private static final String regexFormat = "(\\w|-)+(%s)";
    private static final String regex = String.format(regexFormat, serviceNameSuffix);
    // private static Pattern pattern = Pattern.compile("(\\w|-)+(\\.services\\.dev\\.ofc)");
    private static Pattern pattern = Pattern.compile(regex);
    // private static Pattern pattern = Pattern.compile("^(https?)://[^\\s]*");

    public static void main(String[] args) {
        // String url = "http://test.services.dev.ofc/test/get";
        // String url = "http://test_01.services.dev.ofc/test/get";
        // String url = "http://test-02.services.dev.ofc/test/get";
        // String url = "http://test-02.services.text.jx/test/get";
        // String url = "https://www.baidu.com/query";
        String url = "test";
        Matcher matcher = pattern.matcher(url);
        boolean matchFlag = matcher.find();
        System.out.println(matchFlag);
        if (matchFlag) {
            System.out.println("matcher.groupCount():" + matcher.groupCount());
            String domain = matcher.group();
            System.out.println("matcher.group():" + domain);
            for(int i = 0; i < matcher.groupCount(); i++) {
                System.out.println(i + "=>" + matcher.group(i));
            }

            String appName = domain.substring(0, domain.indexOf(serviceNameSuffix));
            System.out.println(appName);

            System.out.println(url.replace(serviceNameSuffix, ""));
        }
    }
}
