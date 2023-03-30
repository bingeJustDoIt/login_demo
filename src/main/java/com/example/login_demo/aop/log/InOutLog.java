package com.example.login_demo.aop.log;

import java.lang.annotation.*;

/**
 * @program: uegmt-master
 * @description: 对外接口以及调用外部接口的专用log注解
 * @author: HJB
 * @create: 2023-02-20 11:57
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InOutLog {
    /**
     * 日志数据是否入库
     *
     * @return
     */
    InOutLogTypeEnum logType() default InOutLogTypeEnum.NO_SAVE;

    /**
     * 调用方向 区分对外接口还是主动调用外部接口
     *
     * @return
     */
    InOutLogTypeEnum direction() default InOutLogTypeEnum.DEFAULT_INNER;

}
