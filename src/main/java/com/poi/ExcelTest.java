package com.poi;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.io.InputStream;

/**
 * @author wanchongyang
 * @date 2019-04-12 10:22
 */
public class ExcelTest {
    public static void main(String[] args) {
        File file = FileUtil.file("validate_test.xlsx");
        InputStream in = FileUtil.getInputStream(file);
        ExcelUtil.readBySax(in, 0, (sheetIndex, rowIndex, rowList) -> {
            if (rowIndex == 1) {
                throw new IllegalArgumentException();
            }
            System.out.println((rowIndex + 1) + "\t" + rowList);
        });
    }
}
