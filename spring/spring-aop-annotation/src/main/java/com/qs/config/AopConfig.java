package com.qs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author : qshomewy
 * @description : 开启切面配置
 */
@Configuration
@ComponentScan("com.qs.*")
@EnableAspectJAutoProxy// 开启@Aspect注解支持, 等价于<aop:aspectj-autoproxy>
public class AopConfig {
}
