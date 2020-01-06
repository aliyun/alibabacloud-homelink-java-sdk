package com.aliyun.iotx.api.sdk.dto;

import lombok.Data;

/**
 * 查询排序参数
 * @author baobao.xq
 * @date 2018/12/13上午11:54
 */
@SuppressWarnings("WeakerAccess")
@Data
public class OrderBy {

    private String columnName;

    private String direction;
}
