package com.atgui.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 项目名称：cloud2020
 * 类 名 称：ConfigCenterMain3344
 * 类 描 述：TODO
 * 创建时间：2020/5/21 1:09
 * 创 建 人：shuchang
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class,args);
    }
}
