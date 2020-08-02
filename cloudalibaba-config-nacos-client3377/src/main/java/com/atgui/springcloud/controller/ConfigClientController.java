package com.atgui.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称：cloud2020
 * 类 名 称：ConfigClientController
 * 类 描 述：TODO
 * 创建时间：2020/5/23 23:48
 * 创 建 人：shuchang
 */
@RestController
@RefreshScope // 支持Nacos的动态刷新功能
public class ConfigClientController {

    @Value("${config.info}")
    protected String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }

}