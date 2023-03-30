package com.example.login_demo.aop.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: uegmt-master
 * @description:
 * @author: HJB
 * @create: 2023-02-20 11:59
 **/
@Getter
@AllArgsConstructor
public enum InOutLogTypeEnum {

    NO_SAVE(1,"不入库"),
    SAVE(2,"入库"),

    PROVIDE_OUTERS(3,"提供对外接口"),
    GET_FROM_OUTERS(4,"调用外部接口"),

    DEFAULT_INNER(5,"默认平台内部接口")


    ;

    private int code;
    private String msg;


}
