package com.aliyun.iotx.api.sdk.platform.thirdparty;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.iotx.api.sdk.business.homelink.DefaultAssertFunc;
import com.aliyun.iotx.api.sdk.business.homelink.dto.user.IdentityDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.user.ThirdAccountListDTO;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.command.ApiCommand;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Optional;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.*;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;


/**
 * 三方账号API
 *
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
    public static ApiCommand<String> importAccountForThirdParty(String openId,
                                                                String nickName,
                                                                String phone,
                                                                String email,
                                                                String avatarUrl,
                                                                String creater,
                                                                String loginName,
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
     * 按租户维度，批量获取账号
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static ApiCommand<ThirdAccountListDTO> queryThirdAccountList(Integer pageNo, Integer pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_BASE_SEP, "queryThirdAccountList");

        HashMap<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);

        return getApiCommand(apiConfig, params, RETURN_TYPE_THIRD_ACCOUNT_LIST);
    }

    /**
     * 根据三方提供的openId查询IoT的账号信息
     *
     * @param openId
     * @return
     */
    public static ApiCommand<IdentityDTO> getAccountByOpenId(String openId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_BASE_SEP, "getAccountByOpenId");

        HashMap<String, Object> params = Maps.newHashMapWithExpectedSize(1);
        params.put("openId", openId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_IDENTITY);
    }

    /**
     * 校验账号是否已注册
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
