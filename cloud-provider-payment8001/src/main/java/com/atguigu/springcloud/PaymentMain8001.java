package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 项目名称：cloud2020
 * 类 名 称：PaymenMaint8001
 * 类 描 述：TODO
 * 创建时间：2020/5/4 17:21
 * 创 建 人：shuchang
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PaymentMain8001 {
    /**
     * @name: main
     * @description: PaymentService启动类
     * @param args
     * @return: void
     * @date: 2020/5/4 18:48
     * @auther: shuchang
    */
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
