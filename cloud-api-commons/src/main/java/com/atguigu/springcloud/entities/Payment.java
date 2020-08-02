package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 项目名称：cloud2020
 * 类 名 称：Payment
 * 类 描 述：TODO
 * 创建时间：2020/5/4 18:35
 * 创 建 人：shuchang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable{
    private long id;
    private String serial;
}
