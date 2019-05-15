package com.qs.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author : qshomewy
 * @description : 前端控制器配置
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ServletConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
