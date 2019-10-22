package com.aliyun.iotx.api.sdk.business.homelink;

import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.util.assertion.IoTxAssertions;
import com.aliyun.iotx.api.util.command.ApiCommand;
import com.aliyun.iotx.api.util.entity.IoTxResult;

import java.util.HashMap;

import static java.util.Objects.nonNull;


/**
 * API一般工具
 *
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2019/07/25
 */
public final class ApiUtil {

    /**
     * 创建参数映射表，用operator创建一个size大小的参数映射表
     *
     * @param operator 操作员
     * @param size     映射表大小
     * @return Map
     */
    public static HashMap<String, Object> createParamMap(IdentityDTO operator, int size) {

        HashMap<String, Object> params = new HashMap<>(size);
        if (nonNull(operator)) {
            params.put("operator", operator);
        }
        return params;
    }

    /**
     * 创建参数映射表，用operator和iotId创建一个size大小的参数映射表
     *
     * @param operator 操作员
     * @param iotId    设备ID
     * @param size
     * @return Map
     */
    public static HashMap<String, Object> createParamMap(IdentityDTO operator, String iotId, int size) {
        HashMap<String, Object> params = createParamMap(operator, size);
        params.put("iotId", iotId);
        return params;
    }

}
