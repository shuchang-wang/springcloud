package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 项目名称：cloud2020
 * 类 名 称：PaymentController
 * 类 描 述：TODO
 * 创建时间：2020/5/5 21:16
 * 创 建 人：shuchang
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/zk")
    public String paymentzk(){
        return "SpringCloud with Zookeeper："+serverPort+"\t"+ UUID.randomUUID().toString();
    }

}
