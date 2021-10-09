package com.yh.common.domain.dto;

import com.yh.common.util.NumberUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("通用统计数据")
@Data
public class BaseTotalDTO {
    @ApiModelProperty("数量")
    private Integer num;
    @ApiModelProperty("上期数量")
    private Integer lastNum;
    @ApiModelProperty("上年同期数量")
    private Integer lastYearNum;
    @ApiModelProperty("环比")
    private String ringProp;
    @ApiModelProperty("同比")
    private String sameProp;

    /**
     * 通过当期、上期、同期 计算环比和同比
     */
    public void count() {
        this.ringProp = NumberUtil.countIncrPercentage(num, lastNum);
        this.sameProp = NumberUtil.countIncrPercentage(num, lastYearNum);
    }
}
