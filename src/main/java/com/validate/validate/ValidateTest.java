package com.validate.validate;

import com.validate.*;
import com.validate.constant.ValidatorConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author wanchongyang
 * @date 2018/11/14 10:37 AM
 */
public class ValidateTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateTest.class);
    private static final String FILE_NAME = "validate_test_many.xlsx";
    private static final String PARA_NAME = "IMPORT_PARA_TEST";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // /Users/martin/install/IdeaProjects/javaee/target/classes/validate_test.xlsx
        // String path = Thread.currentThread().getContextClassLoader().getResource(FILE_NAME).getPath();
        try {
            // 1）获取导入配置的元数据
            ValidateContext context = initContext();
            long middle = System.currentTimeMillis();
            System.out.println("initContext cost ms:" + (middle - start));

            // 2）获取文件输入流
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME);
            Workbook workbook = ExcelUtil.getWorkbook(in, FILE_NAME);

            // 3）表头验证
            // 读取第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
            // 读取第一行,看模板是否正确
            Row firstRow = sheet.getRow(0);
            if (firstRow.getPhysicalNumberOfCells() != context.getTitleFields().getFieldList().size()) {
                LOGGER.error("title field not matched.");
                return;
            }

            int cellLen = firstRow.getPhysicalNumberOfCells();
            for (int cellNum = 0; cellNum < cellLen; cellNum++) {
                // 获取单元格的值
                String cellValue = ExcelUtil.getValue(firstRow.getCell(cellNum)).trim();
                String temp = context.getTitleFields().getFieldList().get(cellNum).getFieldTranslationMap().get(ValidatorConstant.CN).getShowName();
                if (!temp.equals(cellValue)) {
                    LOGGER.error("title field name not matched.excel:{}-{}", cellValue, temp);
                    return;
                }
            }

            // 4）组装验证数据
            // sheet的总行数
            int totalRowNum = sheet.getLastRowNum();
            ValidateItem validateItem = null;
            // 读取行
            for (int rowNum = 1; rowNum <= totalRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                // 每一行产生一个对象，封装数据
                validateItem = ValidateItem.build();
                // 读取单元格,每行5列
                for (int cellNum = 0; cellNum < cellLen; cellNum++) {
                    String variableName = context.getTitleFields().getFieldList().get(cellNum).getVariableName();
                    String cellValue = StringUtils.trim(ExcelUtil.getValue(row.getCell(cellNum)));
                    validateItem.add(variableName, cellValue);
                }

                context.getItemList().add(validateItem);
            }
            context.setTotalCnt(context.getItemList().size());

            // 5）数据验证
            // 第一种：行验证
            // rowValidate(context);

            // 第二种：列验证
            columnValidate(context);

            // LOGGER.info(JSON.toJSONString(context.getItemList()));
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void rowValidate(ValidateContext context) {
        // 成功总条数
        int successCnt = 0;
        // 失败总条数
        int failCnt = 0;
        for (int j = 0; j < context.getItemList().size(); j++) {
            ValidateItem item = context.getItemList().get(j);
            for (int i = 0; i < context.getTitleFields().getFieldList().size(); i++) {
                if (!item.getValidateResult().isValid()) {
                    break;
                }

                MetadataTitleField titleField = context.getTitleFields().getFieldList().get(i);
                List<BeanValidator> validatorList = titleField.getChain().getChain();
                if (validatorList != null && !validatorList.isEmpty()) {
                    for (BeanValidator validator : validatorList) {
                        validator.getHandler().validate(validator, titleField, item);
                        if (!item.getValidateResult().isValid()) {
                            break;
                        }
                    }
                }
            }

            if (item.getValidateResult().isValid()) {
                successCnt++;
            } else {
                failCnt++;
            }
        }
        context.setSuccessCnt(successCnt);
        context.setFailCnt(failCnt);
    }

    private static void columnValidate(ValidateContext context) {
        int failCnt = 0;
        for (int i = 0; i < context.getTitleFields().getFieldList().size(); i++) {
            MetadataTitleField titleField = context.getTitleFields().getFieldList().get(i);
            List<BeanValidator> validatorList = titleField.getChain().getChain();
            for (int j = 0; j < context.getItemList().size(); j++) {
                ValidateItem item = context.getItemList().get(j);
                if (!item.getValidateResult().isValid()) {
                    continue;
                }

                if (validatorList != null && !validatorList.isEmpty()) {
                    for (BeanValidator validator : validatorList) {
                        validator.getHandler().validate(validator, titleField, item);
                        if (!item.getValidateResult().isValid()) {
                            break;
                        }
                    }
                }

                if (!item.getValidateResult().isValid()) {
                    failCnt++;
                }
            }
        }
        context.setFailCnt(failCnt);
        context.setSuccessCnt(context.getTotalCnt() - failCnt);
    }

    private static ValidateContext initContext() {
        ValidateContext context = new ValidateContext();
        MetadataTitleFields titleFields = new MetadataTitleFields();
        titleFields.addField(new MetadataTitleField("personCode")
                .addFieldTranslation(new MetadataFieldTranslation("personCode", "cn", "工号")));
        titleFields.addField(new MetadataTitleField("personName")
                .addFieldTranslation(new MetadataFieldTranslation("personName", "cn", "姓名")));
        titleFields.addField(new MetadataTitleField("age")
                .addFieldTranslation(new MetadataFieldTranslation("age", "cn", "年龄"))
                .setChain(getChain("age", "validator-config-IntRange.xml")));
        titleFields.addField(new MetadataTitleField("email")
                .addFieldTranslation(new MetadataFieldTranslation("email", "cn", "邮箱"))
                .setChain(getChain("email", "validator-config-Email.xml")));
        titleFields.addField(new MetadataTitleField("cardNo")
                .addFieldTranslation(new MetadataFieldTranslation("cardNo", "cn", "身份证号"))
                .setChain(getChain("cardNo", "validator-config-Regex.xml")));

        context.setTitleFields(titleFields);
        return context;
    }

    private static BeanValidatorChain getChain(String variableName, String configName) {
        try {
            //得到DOM解析器的工厂实例
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //从DOM工厂中获得DOM解析器
            DocumentBuilder db = dbf.newDocumentBuilder();
            //把要解析的xml文档读入DOM解析器
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configName);
            Document doc = db.parse(inputStream);
            Element root = doc.getDocumentElement();

            // 初始化
            BeanValidatorChainFactory chainFactory = BeanValidatorChainFactorySingleton.getInstance();
            return chainFactory.getChain(PARA_NAME, variableName, root);
        } catch (Exception e) {
            LOGGER.error("xml parse error.", e);
        }

        return new BeanValidatorChain();
    }
}
