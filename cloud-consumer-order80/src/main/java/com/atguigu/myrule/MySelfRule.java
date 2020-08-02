package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 项目名称：cloud2020
 * 类 名 称：MySelfRule
 * 类 描 述：TODO
 * 创建时间：2020/5/8 23:31
 * 创 建 人：shuchang
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        return new RandomRule(); // 定义为随机
    }
}