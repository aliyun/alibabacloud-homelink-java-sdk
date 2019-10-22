/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.sdk.platform.thirdparty;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.iotx.api.sdk.business.homelink.DefaultAssertFunc;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.command.ApiCommand;
import com.google.common.collect.Maps;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashMap;
import java.util.Optional;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.G_BASE_SEP;


/**
 * 三方账号API
 *
 * @author alibaba
 * @date 2019/07/30
 */
@SuppressWarnings("WeakerAccess")
public class ThirdPartyApi {

    /**
     * 导入三方账号
     *
     * @param openId   授权码
     * @param nickName 平台类型
     * @return ApiCommand，结果格式见文档
     */
    public static ApiCommand<String> importAccountForThirdParty(@NotBlank String openId,
                                                                @NotBlank String nickName,
                                                                String phone,
                                                                String email,
                                                                String avatarUrl,
                                                                String creater,
                                                                @NotBlank String loginName,
                                                                String password,
                                                                Boolean pwdEncrypted,
                                                                Integer openIdType) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_BASE_SEP, "importAccountForThirdParty");

        HashMap<String, Object> params = Maps.newHashMapWithExpectedSize(16);
        params.put("openId", openId);
        params.put("nickName", nickName);
        Optional.ofNullable(phone).ifPresent(v -> params.put("phone", v));
        Optional.ofNullable(email).ifPresent(v -> params.put("email", v));
        Optional.ofNullable(avatarUrl).ifPresent(v -> params.put("avatarUrl", v));
        Optional.ofNullable(creater).ifPresent(v -> params.put("creater", v));
        Optional.ofNullable(loginName).ifPresent(v -> params.put("loginName", v));
        Optional.ofNullable(password).ifPresent(v -> params.put("password", v));
        Optional.ofNullable(pwdEncrypted).ifPresent(v -> params.put("pwdEncrypted", v));
        Optional.ofNullable(openIdType).ifPresent(v -> params.put("openIdType", v));

        return new ApiCommand<String>(apiConfig)
            .https(true)
            .paramSupplier(() -> params)
            .assertFunc(DefaultAssertFunc::assertIt)
            .convertFunc(json -> {
                JSONObject jo = (JSONObject)json;
                return jo.getString("value");
            });
    }

    /**
     * 导入三方账号
     *
     * @return ApiCommand，结果格式见文档
     */
    public static ApiCommand<Boolean> checkAccountRegistered(String phone,
                                                             String email) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_BASE_SEP, "checkAccountRegistered");

        HashMap<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        Optional.ofNullable(phone).ifPresent(v -> params.put("phone", v));
        Optional.ofNullable(email).ifPresent(v -> params.put("email", v));

        return new ApiCommand<Boolean>(apiConfig)
            .https(true)
            .paramSupplier(() -> params)
            .assertFunc(DefaultAssertFunc::assertIt)
            .convertFunc(json -> {
                JSONObject jo = (JSONObject)json;
                String value = jo.getString("value");
                return Boolean.parseBoolean(value);
            });
    }

}
