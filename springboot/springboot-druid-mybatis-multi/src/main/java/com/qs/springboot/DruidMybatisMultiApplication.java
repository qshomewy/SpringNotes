package com.qs.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 
 * @author : qshomewy
 * @description :
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DruidMybatisMultiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DruidMybatisMultiApplication.class, args);
    }

}

