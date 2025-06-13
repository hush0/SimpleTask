package com.hush.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: huangshuai
 * @Date: 6/13/25
 * @Version 1.0
 */

@EnableAsync
@EnableConfigurationProperties
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AppBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(AppBootStrap.class, args);
    }

}
