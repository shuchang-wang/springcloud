package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 项目名称：cloud2020
 * 类 名 称：PaymentFeginService
 * 类 描 述：TODO
 * 创建时间：2020/5/10 14:17
 * 创 建 人：shuchang
 */
@Component
//如果只加注解@FeignClient不加value值则报异常java.lang.IllegalStateException: Either 'name' or 'value' must be provided in @FeignClient
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
