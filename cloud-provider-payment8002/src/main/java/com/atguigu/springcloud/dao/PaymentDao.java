package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称：cloud2020
 * 类 名 称：PaymentDao
 * 类 描 述：TODO
 * 创建时间：2020/5/4 18:47
 * 创 建 人：shuchang
 */
@Mapper
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
