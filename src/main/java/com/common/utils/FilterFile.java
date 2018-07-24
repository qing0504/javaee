package com.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 去除文件空行
 * @author wanchongyang
 * @date 2018/7/24 上午10:23
 */
public class FilterFile {
    static String moduleName = "festival-api";
    static String targetPath = "/Users/martin/install/festival/" + moduleName;

    public static void main(String[] args) throws Exception {

        String sourcePath = "/Users/martin/install/git/festival/" + moduleName;

        List<File> list = getChildrenFile(sourcePath);

        foreachFile(list);

        System.out.println("end............");
    }

    static void foreachFile(List<File> list) throws Exception {
        for (File file : list) {
            if (file.isDirectory()) {
                foreachFile(getChildrenFile(file.getAbsolutePath()));
            } else if (file.getName().endsWith(".java") && file.getName().indexOf("Test") < 0) {
                StringBuilder sb = new StringBuilder();
                try (
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                ) {
                    String line = reader.readLine();
                    boolean skip = false;
                    while (line != null) {
                        if (StringUtils.isNotBlank(line)) {
                            sb.append(line).append("\r\n");
                        }
                        line = reader.readLine();
                    }
                }
                if (sb.length() > 0) {
                    String t = file.getAbsoluteFile().getParent();
                    String path = t.substring(t.indexOf("java"));

                    File directory = new File(targetPath + "/" + path);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    try (OutputStream outputStream = new FileOutputStream(new File(directory, file.getName()))) {
                        outputStream.write(sb.toString().getBytes());
                        outputStream.flush();
                    }
                }
            }
        }
    }


    static List<File> getChildrenFile(String path) {
        File directory = new File(path);
        if (directory.isDirectory()) {
            return Arrays.asList(directory.listFiles());
        }
        return new ArrayList<>();
    }

}
