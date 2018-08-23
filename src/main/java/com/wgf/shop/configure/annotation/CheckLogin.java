package com.wgf.shop.configure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 用于限制登录用户访问
 * @Author wanggaofeng
 * @Date 2018/8/15 14:11
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})//注解使用位置：表明注解可使用在函数上
@Retention(RetentionPolicy.RUNTIME)//运行时生效
public @interface CheckLogin {
}
