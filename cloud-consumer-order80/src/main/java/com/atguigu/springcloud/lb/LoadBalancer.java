package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 项目名称：cloud2020
 * 类 名 称：LoadBalancer
 * 类 描 述：TODO
 * 创建时间：2020/5/10 11:59
 * 创 建 人：shuchang
 */
public interface LoadBalancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
