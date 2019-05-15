package com.qs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

/**
 * @author : qshomewy
 * @description : 主配置类
 */
@Configuration
@EnableWebMvc
@DubboComponentScan(basePackages = "com.qs.service") // 扫描dubbo注解
@ComponentScan(basePackages = {"com.qs.*"})
public class ServletConfig implements WebMvcConfigurer {

}
