package com.validate.validate;

import com.common.utils.CloseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel操作工具类
 * @author wanchongyang
 * @date 2017/10/20
 */
public class ExcelUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    private static Map<String, CellStyle> cellStyleMap = new HashMap<>();

    private static CellStyle getCellStyle(Workbook wb, String cellStyleStr) {
        if (cellStyleMap.containsKey(cellStyleStr)) {
            return cellStyleMap.get(cellStyleStr);
        } else {
            CellStyle cellStyle = wb.createCellStyle();
            //此处设置数据格式
            DataFormat df = wb.createDataFormat();
            cellStyle.setDataFormat(df.getFormat(cellStyleStr));
            cellStyleMap.put(cellStyleStr, cellStyle);
            return cellStyle;
        }
    }

    /**
     * 处理获取表格的数据类型
     * @param cell
     * @return
     */
    public static String getValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        String strCell = "";
        switch (cell.getCellTypeEnum()){
            case STRING:
                strCell = cell.getStringCellValue().trim();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 如果是date类型则 ，获取该cell的date值
                    strCell = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                } else { // 纯数字
                    cell.setCellType(CellType.STRING);
                    strCell = cell.getStringCellValue().trim();
                }
                break;
            case BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }

        return strCell;
    }

    /**
     * 获取Workbook对象，excel不同版本对应不同的Workbook子类对象
     * @param inputStream
     * @param fileName
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileName) throws IOException {
        if (StringUtils.isBlank(fileName)) {
            return null;
        }

        // 获取操作excel对象
        Workbook workbook = null;
        if (fileName.toLowerCase().endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileName.toLowerCase().endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    public static void writeExcel(String filePath, String fileName, List<Map> list, String[] titles, String[] keys, Boolean appendFlag) {
        // 路径字符串优化
        filePath = filePath.replace("\\\\", "\\");
        // 内存中保留的行数，超出后会写到磁盘
        int rowAccessWindowSize = 1000;
        int totalRows; // 统计总行数
        File file = new File(filePath + fileName);
        BufferedOutputStream out = null;
        Workbook wb = null;
        try {
            Sheet sheet = null;
            if (!appendFlag) {
                wb = new SXSSFWorkbook(rowAccessWindowSize);
                sheet = wb.createSheet();
                Row row = sheet.createRow(0);
                for (int j = 0; j < titles.length; j++) {
                    createCell(wb, row, j, titles[j]);
                }
                totalRows = 1;
            } else {
                wb = new XSSFWorkbook(new FileInputStream(filePath + fileName));
                sheet = wb.getSheetAt(0);
                totalRows = sheet.getPhysicalNumberOfRows();
            }
            for (int i = 0; i < list.size(); i++) {
                Map map = list.get(i);
                sheet.setDefaultColumnWidth(30);
                Row row1 = sheet.createRow(i + totalRows);
                for (int k = 0; k < keys.length; k++) {
                    createCell(wb, row1, k, map.get(keys[k]));
                }

            }
            out = new BufferedOutputStream(new FileOutputStream(file));
            out.flush();
            wb.write(out);
        } catch (Exception e) {
            // 写失败
            LOGGER.error("Create Excel fail :" + e.getMessage());
        } finally {
            CloseUtils.close(out);
            CloseUtils.close(wb);
        }
    }

    public static void writeExcel2(Workbook wb, Sheet sheet, List<Map> list, String[] titles, String[] keys) {
        // 统计总行数第一行从1开始，没有行是0
        int totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows == 0 || totalRows == 1) {
            Row row = sheet.createRow(0);
            for (int j = 0; j < titles.length; j++) {
                createCell(wb, row, j, titles[j]);
            }
        }
        totalRows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            Row row1 = sheet.createRow(i + totalRows);
            for (int k = 0; k < keys.length; k++) {
                createCell(wb, row1, k, map.get(keys[k]));
            }
        }
        list.clear();
    }

    public static void createCell(Workbook wb, Row row, int column, Object ob) {
        Cell cell = row.createCell(column);

        String value;
        if (ob == null || ob.equals("")) {
            value = "";
        } else {
            value = ob.toString();
        }

        if (ob instanceof Double) {
            cell.setCellValue(Double.parseDouble(value));
            CellStyle cellStyle = getCellStyle(wb, "0.00");
            cell.setCellStyle(cellStyle);
        } else {
            cell.setCellValue(value);
        }
    }

}
