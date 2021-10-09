package com.yh.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 数字计算工具类 api
 */
public class NumberUtil {

    //百分比格式化模板
    private static final String PERCENTAGE_PATTERN = "#0.00%";
    /**
     * 计算两个整数相除百分比
     * a除数，b为被除数
     * 如果b为0，则默认为1
     */
    public static String countPercentage(int a, int b) {
        if (b == 0) {
            b = 1;
        }
        DecimalFormat decimalFormat = new DecimalFormat(PERCENTAGE_PATTERN);
        return decimalFormat.format((float) a / b);
    }

    /**
     * 计算a相对于b的增长率
     */
    public static String countIncrPercentage(int a, int b) {
        int c = a - b;
        return countPercentage(c, b);
    }

    public static  double formatDouble(double d){
        BigDecimal bigDecimal = BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }

}
