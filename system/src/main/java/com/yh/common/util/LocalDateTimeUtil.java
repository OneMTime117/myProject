package com.yh.common.util;

import cn.hutool.core.util.StrUtil;
import com.yh.common.domain.dto.BaseDateDTO;
import com.yh.common.domain.dto.CommonDateDTO;
import com.yh.common.exception.BusinessException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * JDK日期、时间 api工具类
 */
public class LocalDateTimeUtil {
    //格式化器
    public static final DateTimeFormatter DATA_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATA_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    //时间00:00:00
    public static final LocalTime START_TIME = LocalTime.of(0, 0, 0);
    //时间23:59:59
    public static final LocalTime END_TIME = LocalTime.of(23, 59, 59);

    //获取今日、本周、上周、本月、上月、本年、今年、去年起止时间
    public static CommonDateDTO getCommonDateDTO(String type) {
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        //需要组装的数据
        LocalDate nowDate = LocalDate.now();
        LocalDate currentStartDate = null;
        LocalDate currentEndDate = null;

        LocalDate lastStartDate = null;
        LocalDate lastEndDate = null;

        LocalDate lastYearStartDate = null;
        LocalDate lastYearEndDate = null;

        BaseDateDTO current = new BaseDateDTO();
        BaseDateDTO last = new BaseDateDTO();
        BaseDateDTO lastYear = new BaseDateDTO();
        switch (type) {
            case "0":
                currentStartDate = nowDate;
                currentEndDate = currentStartDate;
                //昨天、去年当日
                lastStartDate = nowDate.minusDays(1);
                lastEndDate = lastStartDate;
                break;
            case "1"://本周
                currentStartDate = nowDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                currentEndDate = nowDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                //上周
                lastStartDate = nowDate.minusDays(7).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                lastEndDate = nowDate.minusDays(7).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
            case "2"://上周
                currentStartDate = nowDate.minusDays(7).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                currentEndDate = nowDate.minusDays(7).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

                lastStartDate = nowDate.minusDays(14).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                lastEndDate = nowDate.minusDays(14).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
            case "3"://本月
                currentStartDate = nowDate.with(TemporalAdjusters.firstDayOfMonth());
                currentEndDate = nowDate.with(TemporalAdjusters.lastDayOfMonth());

                lastStartDate = nowDate.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
                lastEndDate = nowDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
                break;
            case "4"://上月
                currentStartDate = nowDate.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
                currentEndDate = nowDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

                lastStartDate = nowDate.minusMonths(2).with(TemporalAdjusters.firstDayOfMonth());
                lastEndDate = nowDate.minusMonths(2).with(TemporalAdjusters.lastDayOfMonth());
                break;
            case "5"://本年
                currentStartDate = nowDate.with(TemporalAdjusters.firstDayOfYear());
                currentEndDate = nowDate.with(TemporalAdjusters.lastDayOfYear());

                lastStartDate = nowDate.minusYears(1).with(TemporalAdjusters.firstDayOfYear());
                lastEndDate = nowDate.minusYears(1).with(TemporalAdjusters.lastDayOfYear());
                break;
            case "6"://去年
                currentStartDate = nowDate.minusYears(1).with(TemporalAdjusters.firstDayOfYear());
                currentEndDate = nowDate.minusYears(1).with(TemporalAdjusters.lastDayOfYear());

                lastStartDate = nowDate.minusYears(2).with(TemporalAdjusters.firstDayOfYear());
                lastEndDate = nowDate.minusYears(2).with(TemporalAdjusters.lastDayOfYear());
                break;
            default:
                new BusinessException("参数内容异常");
        }
        //去年同期
        lastYearStartDate = currentStartDate.minusYears(1);
        lastYearEndDate = currentEndDate.minusYears(1);

        //当前
        current.setStartDate(LocalDateTime.of(currentStartDate, START_TIME));
        current.setEndDate(LocalDateTime.of(currentEndDate, END_TIME));
        //上期
        last.setStartDate(LocalDateTime.of(lastStartDate, START_TIME));
        last.setEndDate(LocalDateTime.of(lastEndDate, END_TIME));
        //同期
        lastYear.setStartDate(LocalDateTime.of(lastYearStartDate, START_TIME));
        lastYear.setEndDate(LocalDateTime.of(lastYearEndDate, END_TIME));
        return new CommonDateDTO(current, last, lastYear);
    }

    /**
     * 获取指定年\月\日起到今天的时间段
     * */
    public static BaseDateDTO getYearMonthDayDateToNow(int year,int month,int day) {
        LocalDateTime start = LocalDateTime.of(LocalDate.of(year, month, day), LocalDateTimeUtil.START_TIME);
        LocalDateTime now = LocalDateTime.now();
        BaseDateDTO baseDateDTO = new BaseDateDTO();
        baseDateDTO.setStartDate(start);
        baseDateDTO.setEndDate(now);
        return baseDateDTO;
    }

    /***
     * 获取前N天到今天的时间段
     * */
    public static BaseDateDTO getAfterDayToNow(int day){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of( now.minusDays(day).toLocalDate(),LocalDateTimeUtil.START_TIME);
        BaseDateDTO baseDateDTO = new BaseDateDTO();
        baseDateDTO.setStartDate(start);
        baseDateDTO.setEndDate(now);
        return baseDateDTO;
    }
    /**
     * 获取前N个月到今天的时间段
     * */
    public static BaseDateDTO getAfterMonthToNow(int month){
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.minusMonths(month);
        LocalDateTime start = LocalDateTime.of(localDate, LocalDateTimeUtil.START_TIME);
        LocalDateTime end = LocalDateTime.of(now,LocalDateTimeUtil.END_TIME);
        BaseDateDTO baseDateDTO = new BaseDateDTO();
        baseDateDTO.setStartDate(start);
        baseDateDTO.setEndDate(end);
        return baseDateDTO;
    }

    /**
     * 获取第前N个月的开始-结束时间
     * month=12, now=2021-10-1 return 2020-10-1  2020-10-31
     * */
    public static BaseDateDTO getAfterMonth(int month){
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.minusMonths(month);
        LocalDateTime start = LocalDateTime.of(localDate.with(TemporalAdjusters.firstDayOfMonth()), LocalDateTimeUtil.START_TIME);
        LocalDateTime end = LocalDateTime.of(localDate.with(TemporalAdjusters.lastDayOfMonth()), LocalDateTimeUtil.END_TIME);
        BaseDateDTO baseDateDTO = new BaseDateDTO();
        baseDateDTO.setStartDate(start);
        baseDateDTO.setEndDate(end);
        return baseDateDTO;
    }

    /**
     * 判断当前时间,是否在某个时间段,如9-20
     * 如果时间段为空,则默认为true
     * */
    public static boolean assertNowInRangeTime(String rangeTime){
        if (StrUtil.isNotBlank(rangeTime)){
            String[] split = rangeTime.split("-");
            int startHour = Integer.valueOf(split[0]);
            int endHour = Integer.valueOf(split[1]);
            //判断当前时间是否在该区间内
            LocalDateTime now = LocalDateTime.now();
            int value = now.getMonth().getValue();
            if (value<=endHour&&value>=startHour){
                return true;
            }else {
                return false;
            }
        }
        return true;
    }
}
