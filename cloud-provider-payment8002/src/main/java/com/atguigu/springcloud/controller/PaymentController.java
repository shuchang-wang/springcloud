package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称：cloud2020
 * 类 名 称：PaymentController
 * 类 描 述：TODO
 * 创建时间：2020/5/4 19:37
 * 创 建 人：shuchang
 */
@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("**************插入结果："+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功！serverPort："+serverPort,result);
        }
        return new CommonResult(444,"插入数据库失败！serverPort："+serverPort,null);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("**************查询结果："+payment+"\t"+"O(∩_∩)O哈哈~");
        if(payment!=null){
            return new CommonResult(200,"查询成功！serverPort："+serverPort,payment);
        }
        return new CommonResult(444,"没有对应记录，查询ID："+id+"serverPort："+serverPort,null);
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
}