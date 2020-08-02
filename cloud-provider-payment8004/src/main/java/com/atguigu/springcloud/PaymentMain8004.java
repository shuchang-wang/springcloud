package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 项目名称：cloud2020
 * 类 名 称：PaymentMain8004
 * 类 描 述：TODO
 * 创建时间：2020/5/5 21:07
 * 创 建 人：shuchang
 */
@SpringBootApplication
@EnableDiscoveryClient
//该注解用于向使用consul或者Zookeeper作为注册中心时注册服务
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);
    }
}
