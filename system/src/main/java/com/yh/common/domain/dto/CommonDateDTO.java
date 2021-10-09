package com.yh.common.domain.dto;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("通用日期时间")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonDateDTO {
    //当前时间段
    private BaseDateDTO currentDate;
    //上期时间段
    private BaseDateDTO lastDate;
    //去年同期时间段
    private BaseDateDTO lastYearDate;
}
