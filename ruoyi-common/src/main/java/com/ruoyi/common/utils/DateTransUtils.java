package com.ruoyi.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.util.Date;

public class DateTransUtils {
    public static void main(String[] args) {
        // 示例调用
        String inputDate = "2023-03-15";

        int result = getDateValue(inputDate, 1);
        int result1 = getDateValue(inputDate, 2);
        int result2 = getDateValue(inputDate, 3);
        int result3 = getDateValue(inputDate, 4);
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }

    // 公共函数
    public static int getDateValue(String inputDate, int type) {
        LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        switch (type) {
            case 1:
                return date.get(ChronoField.DAY_OF_YEAR);
            case 2:
                return date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
            case 3:
                return date.getMonthValue();
            case 4:
                return date.get(IsoFields.QUARTER_OF_YEAR);
            case 5:
                return 0;
            case 6:
                return 0;
            default:
                throw new IllegalArgumentException("Unsupported DateType: " + type);
        }
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 使用 SimpleDateFormat 格式化日期为字符串
        String dateString = sdf.format(date);
        return dateString;
    }

}
