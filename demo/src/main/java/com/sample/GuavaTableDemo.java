package com.sample;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;

import java.util.Map;
import java.util.Set;

/**
 * Guava Table示例
 * 双键的Map--〉table
 * rowKey+columnKey+value
 * 方法：
 * 所有行数据：cellSet
 * 所有学生：rowKeySet
 * 所有课程：columnKeySet
 * 所有成绩：values
 * 学生对应课程：rowMap+get/row
 * 课程对应学生 columnMap+get/column
 * Created by Martin on 2017/3/20.
 */
public class GuavaTableDemo {
    private static String TAB_SPACE = "\t";

    public static void main(String[] args) {
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("张三", "java", 80);
        table.put("张三", "mysql", 90);
        table.put("李四", "mysql", 85);
        table.put("王二", "java", 96);

        System.out.println("---------按学生查看成绩--------");
        System.out.print("学生" + TAB_SPACE);
        //获取所有课程
        Set<String> columnKeySets = table.columnKeySet();
        for (String c : columnKeySets) {
            System.out.print(c + TAB_SPACE);
        }

        System.out.println();
        //获得所有学生 + 成绩
        Set<String> rowKeySets = table.rowKeySet();
        for (String rowKey : rowKeySets) {
            System.out.print(rowKey + TAB_SPACE);
            Map<String, Integer> coursesMap = table.row(rowKey);
            for (String cKey : columnKeySets) {
                System.out.print((coursesMap.get(cKey) == null ? "-" : coursesMap.get(cKey)) + TAB_SPACE);
            }
            System.out.println();
        }

        System.out.println("---------按课程查看成绩--------");
        // 获得所有学生
        System.out.print("课程" + TAB_SPACE);
        for (String r : rowKeySets) {
            System.out.print(r + TAB_SPACE);
        }

        System.out.println();

        //获得所有课程 + 成绩
        for (String c : columnKeySets) {
            Map<String, Integer> columnMap = table.column(c);
            System.out.print(c + TAB_SPACE);
            for (String r : rowKeySets) {
                System.out.print((columnMap.get(r) == null ? "-" : columnMap.get(r)) + TAB_SPACE);
            }
            System.out.println();
        }

        System.out.println("---------转换--------");
        //将列调换，由学生-课程-成绩表变为 课程-学生-成绩
        Table<String,String,Integer> newTable = Tables.transpose(table);
        // 得到所有行数据
        Set<Table.Cell<String, String, Integer>> cells = newTable.cellSet();
        for (Table.Cell<String, String, Integer> temp : cells) {
            System.out.println(temp.getRowKey() + " " + temp.getColumnKey()+ " " + temp.getValue());
        }

    }
}
