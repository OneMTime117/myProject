package com.yh.common.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@ApiModel("分组统计数据")
@Data
public class BaseGroupDTO {
    //分组名
    private String groupName;
    //数量
    private Integer num;
    //百分比
    private String prop;

    //将每组数据转化为坐标统计
    public static BaseEachTotalDTO dataBind(List<BaseGroupDTO> baseGroupDTOS){
        ArrayList<String> xData = new ArrayList<>();
        ArrayList<Integer> yData = new ArrayList<>();
        for (BaseGroupDTO baseGroupDTO : baseGroupDTOS) {
            xData.add(baseGroupDTO.getGroupName());
            yData.add(baseGroupDTO.getNum());
        }
        BaseEachTotalDTO baseEachTotalDTO = new BaseEachTotalDTO();
        baseEachTotalDTO.setXData(xData);
        baseEachTotalDTO.setYData(yData);
        return baseEachTotalDTO;
    }
}
