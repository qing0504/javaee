package com.poi;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.InputStream;

/**
 * @author wanchongyang
 * @date 2019/10/17 6:45 下午
 */
public class ExportFileTest {
    public static void main(String[] args) {
        // String fileName = "export_client_test.xlsx";
        String fileName = "temp.xlsx";
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(fileName));
        System.out.println(reader.isIgnoreEmptyRow());
        System.out.println("getPhysicalNumberOfRows:" + reader.getSheet().getPhysicalNumberOfRows());
        System.out.println("getLastRowNum:" + reader.getSheet().getLastRowNum());

        InputStream inputStream = FileUtil.getInputStream(fileName);
        ExcelUtil.readBySax(inputStream, 0, (sheetIndex, rowIndex, rowList) -> {
            System.out.println(rowIndex + "===============" + rowList);
        });
    }
}
