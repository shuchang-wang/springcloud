package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.MySelfLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.net.URI;
import java.util.List;

/**
 * 项目名称：cloud2020
 * 类 名 称：OrderController
 * 类 描 述：TODO
 * 创建时间：2020/5/4 21:23
 * 创 建 人：shuchang
 */
@RestController
@Slf4j
public class OrderController {

//    private static final String PAYMENT_URL = "http://localhost:8001";
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MySelfLoadBalancer mySelfLoadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("调用了   /consumer/payment/create");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("调用了   /consumer/payment/get/"+id);
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/get/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            log.info(responseEntity.getStatusCode()+"\t"+responseEntity.getHeaders()+"\t"+responseEntity.getBody());
            return  responseEntity.getBody();
        }else{
            return new CommonResult(444,"查询失败！");
        }
    }

    @GetMapping(value = "/consumer/payment/postForEntity/create")
    public CommonResult<Payment> create2(Payment payment){
        log.info("调用了   /consumer/payment/create");
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            log.info(responseEntity.getStatusCode()+"\t"+responseEntity.getHeaders()+"\t"+responseEntity.getBody());
            return  responseEntity.getBody();
        }else{
            return new CommonResult<>(444,"新增失败！");
        }
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size()<= 0){
            return null;
        }else{
            ServiceInstance serviceInstance = mySelfLoadBalancer.instance(instances);
            URI uri = serviceInstance.getUri();
            return restTemplate.getForObject(uri + "/payment/lb",String.class);
        }
    }

    @GetMapping(value="/consumer/payment/zipkin")
    public String paymentZipkin() {
        return restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin/",String.class);
    }
}