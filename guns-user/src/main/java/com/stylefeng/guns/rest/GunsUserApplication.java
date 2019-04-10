package com.stylefeng.guns.rest;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.stylefeng.guns"})
@EnableDubboConfiguration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.stylefeng.guns.rest.*.dao", "com.stylefeng.guns.rest.common.persistence.dao"})

public class GunsUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunsUserApplication.class, args);
    }
}
