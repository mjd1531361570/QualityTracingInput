package com.xintong.qualitytracinginput.utils.bindView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// Created by 马军达 on 2018/1/30.

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {//绑定视图
    int value();
    boolean click() default false;
}
