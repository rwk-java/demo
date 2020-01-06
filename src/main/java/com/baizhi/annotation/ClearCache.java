package com.baizhi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)             // 声明位置  使用在方法上
@Retention(RetentionPolicy.RUNTIME)     // 方法运行时生效
public @interface ClearCache {
}
