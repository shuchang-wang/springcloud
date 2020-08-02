package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 项目名称：cloud2020
 * 类 名 称：OrderHystrixController
 * 类 描 述：TODO
 * 创建时间：2020/5/12 22:09
 * 创 建 人：shuchang
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;

    }
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "15000")
//    })
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        int i = 1/0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }
    public String paymentInfo_TimeoutHandler(Integer id){
        return "我是消费者80，对方系统8001系統繁忙请10秒后再试或者自己运行出错请检查自己o(╥﹏╥)o";
    }
    //下面是GLobal全局异常处理
    public String payment_Global_FallbackMethod(){
        return "GLOBAL异常，请稍后重试！o(╥﹏╥)o";
    }
}
