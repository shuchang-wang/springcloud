package com.atgui.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 项目名称：cloud2020
 * 类 名 称：OrderConsulController
 * 类 描 述：TODO
 * 创建时间：2020/5/7 22:15
 * 创 建 人：shuchang
 */
@RestController
@Slf4j
public class OrderConsulController {

    private static final String INVOKE_URL = "http://consul-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String getPaymentInfo(){
        return restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
    }
}
