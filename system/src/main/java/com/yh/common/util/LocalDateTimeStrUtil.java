package com.yh.common.util;

import com.yh.common.domain.dto.BaseDateDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanhuan
 * @description 时间转字符串处理
 * @date 2021/10/27 15:34
 */
public class LocalDateTimeStrUtil {

	public static final DateTimeFormatter TIME_MILLIS_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	public static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy_MM");

	/**
	 * 获取当前时间的字符串序列，精确到毫米
	 */
	public static String currentTimeMillisStr() {
		LocalDateTime now = LocalDateTime.now();
		return now.format(LocalDateTimeStrUtil.TIME_MILLIS_FORMATTER);
	}

	/**
	 * @description: 获取一个时间段内, 所有的年_月
	 */
	public static List<String> getYearMonthByBaseDate(BaseDateDTO dateDto) {
		List<String> strList = new ArrayList<>();
		LocalDate startDate = dateDto.getStartDate().toLocalDate();
		LocalDate endDate = dateDto.getEndDate().toLocalDate();
		//获取时间段内的年_月
		LocalDate currentDate = startDate;
		while (currentDate.compareTo(endDate) <= 0) {
			strList.add(currentDate.format(LocalDateTimeStrUtil.YEAR_MONTH_FORMATTER));
			currentDate = currentDate.plusMonths(1);
		}
		return strList;
	}

	/**
	 * @description: 获取前N个月到现在, 所有的年_月
	 */
	public static List<String> eachMonthStrBeforeMonth(int month) {
		List<String> resultList = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		for (int i = month; i >= 0; i--) {
			LocalDateTime localDateTime = now.minusMonths(i);
			String format = localDateTime.format(LocalDateTimeStrUtil.YEAR_MONTH_FORMATTER);
			resultList.add(format);
		}
		return resultList;
	}

}
