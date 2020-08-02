package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 项目名称：cloud2020
 * 类 名 称：PaymentConsulMain8006
 * 类 描 述：TODO
 * 创建时间：2020/5/6 23:10
 * 创 建 人：shuchang
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentConsulMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentConsulMain8006.class,args);
    }
}
