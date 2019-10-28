package com.aliyun.iotx.api.sdk;

import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.util.command.ApiEnvironment;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 测试账号的初始化
 *
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2019/09/19
 */
@SuppressWarnings("WeakerAccess")
public class TestAccounts {

    public static final ApiEnv EMPTY_ENV;

    static {
        // EMPTY_ENV
        {
            IdentityDTO operator = new IdentityDTO();
            operator.setHid("xxx");
            operator.setHidType("IOT");

            ApiEnvironment env = ApiEnvironment.builder()
                .name("default")
                // 获取appKey/appSecret 参考 https://homelink.iot.aliyun.com/doc#cynigt.html
                .appKey("xxx")
                .appSecret("xxx")
                .host("api.link.aliyun.com")
                .requestTimeout(30_000L)
                .build();

            EMPTY_ENV = new ApiEnv(env, operator);
        }

    }

    @Data
    @AllArgsConstructor
    public static class ApiEnv {

        ApiEnvironment env;

        IdentityDTO operator;
    }
}
