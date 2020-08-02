package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 项目名称：cloud2020
 * 类 名 称：ConfigClientMain3355
 * 类 描 述：TODO
 * 创建时间：2020/5/21 23:07
 * 创 建 人：shuchang
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3355.class,args);
    }
}


