package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 项目名称：cloud2020
 * 类 名 称：FeignConfig
 * 类 描 述：TODO
 * 创建时间：2020/5/10 18:29
 * 创 建 人：shuchang
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
