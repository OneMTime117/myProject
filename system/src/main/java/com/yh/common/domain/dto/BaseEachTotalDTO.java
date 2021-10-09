package com.yh.common.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

//折线图 、柱状图统计
@Data
@ApiModel("折线图 、柱状图统计")
public class BaseEachTotalDTO {
    private List<String> xData;
    private List<Integer> yData;
}
