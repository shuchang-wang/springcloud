package com.atgui.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 项目名称：cloud2020
 * 类 名 称：NacosConfigClientMain3377
 * 类 描 述：TODO
 * 创建时间：2020/5/23 23:46
 * 创 建 人：shuchang
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigClientMain3377 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientMain3377.class,args);
    }
}
