package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称：cloud2020
 * 类 名 称：PaymentService
 * 类 描 述：TODO
 * 创建时间：2020/5/4 19:20
 * 创 建 人：shuchang
 */
public interface PaymentService{
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}