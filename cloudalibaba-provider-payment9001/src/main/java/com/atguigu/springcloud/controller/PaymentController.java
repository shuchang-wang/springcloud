package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称：cloud2020
 * 类 名 称：PaymentController
 * 类 描 述：TODO
 * 创建时间：2020/5/23 22:21
 * 创 建 人：shuchang
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private  String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "Hello Nacos Discovery: " + serverPort + "\t id: " + id;
    }

}
