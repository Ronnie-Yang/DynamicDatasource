package com.example.dynamicdatasource.config.dds;

import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    /**
     * 数据源 key 值
     * @return
     */
    String value();
}
