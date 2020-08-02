package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 项目名称：cloud2020
 * 类 名 称：ApplicationContextConfig
 * 类 描 述：TODO
 * 创建时间：2020/5/4 21:46
 * 创 建 人：shuchang
 */
@Configuration
public class ApplicationContextConfig {//作用类似于applicationContext.xml的 <bean id="xxx" class="XXX"/>

    @Bean
//    @LoadBalanced
    //使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
