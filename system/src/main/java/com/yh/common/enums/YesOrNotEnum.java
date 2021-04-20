package com.yh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YesOrNotEnum {

    YES("Y","是"),
    NO("N","否");

    private String code;
    private String msg;
}
