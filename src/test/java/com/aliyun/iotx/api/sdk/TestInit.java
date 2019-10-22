/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.sdk;

import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.util.command.ApiEnvironment;
import com.aliyun.iotx.api.util.context.ApiContext;
import lombok.Getter;


/**
 * 测试环境初始化类，不用在每个测试中都执行环境初始化
 *
 * @author alibaba
 * @date 2019/09/20
 */
public class TestInit {

    @Getter
    public static ApiEnvironment env;

    @Getter
    public static IdentityDTO operator;

    /**
     * 初始化环境，此方法手动修改
     */
    public static void init() {
        // TODO 手动修改，选择测试用的账号
        TestAccounts.ApiEnv apiEnv = TestAccounts.EMPTY_ENV;

        operator = initEnv(apiEnv, true, false);
        env = apiEnv.env;
    }

    /**
     * 初始化环境
     *
     * @param apiEnv    环境
     * @param isDefault 是否为默认环境
     * @param isPre     是否预发，一般为false
     * @return operator 操作员
     */
    @SuppressWarnings("SameParameterValue")
    private static IdentityDTO initEnv(TestAccounts.ApiEnv apiEnv, boolean isDefault, boolean isPre) {
        ApiContext.setEnv(apiEnv.getEnv(), isDefault);
        ApiContext.setPre(isPre);

        return apiEnv.getOperator();
    }
}
