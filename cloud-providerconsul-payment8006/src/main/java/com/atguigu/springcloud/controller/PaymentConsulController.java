package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 项目名称：cloud2020
 * 类 名 称：PaymentConsulController
 * 类 描 述：TODO
 * 创建时间：2020/5/6 23:17
 * 创 建 人：shuchang
 */
@RestController
@Slf4j
public class PaymentConsulController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String paymentConsul(){
        return "SpringCloud with consul："+serverPort+"\t"+ UUID.randomUUID().toString();
    }

}
