package com.yh.common.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel("时间传参")
@Data
public class BaseDateDTO {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
