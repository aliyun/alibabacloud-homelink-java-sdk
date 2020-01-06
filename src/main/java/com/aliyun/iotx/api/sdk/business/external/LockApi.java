package com.aliyun.iotx.api.sdk.business.external;

import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.command.ApiCommand;

import javax.annotation.Nullable;
import java.util.HashMap;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.*;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiUtil.createParamMap;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;


/**
 * 锁相关API定义
 *
 * @date 2019/10/31
 */
public class LockApi {

    /**
     * 创建静态密码
     *
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<String> createStaticPassword(@Nullable IdentityDTO operator,
                                                          String iotId,
                                                          Integer passwordIndex,
                                                          @Nullable String password,
                                                          @Nullable String ruleData) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_EXT_LOCK, "lockStaticPasswordCreate");

        HashMap<String, Object> params = createParamMap(operator, iotId, 8);
        params.put("passwordIndex", passwordIndex);
        params.put("password", password);
        params.put("ruleData", ruleData);

        return getApiCommand(apiConfig, params, RETURN_TYPE_STRING);
    }

    /**
     * 更新静态密码
     *
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<String> updateStaticPassword(@Nullable IdentityDTO operator,
                                                          String iotId,
                                                          Integer passwordIndex,
                                                          String oldPassword,
                                                          String newPassword,
                                                          @Nullable String ruleData) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_EXT_LOCK, "lockStaticPasswordUpdate");
        HashMap<String, Object> params = createParamMap(operator, iotId, 8);
        params.put("passwordIndex", passwordIndex);
        params.put("oldPassword", oldPassword);
        params.put("newPassword", newPassword);
        params.put("ruleData", ruleData);

        return getApiCommand(apiConfig, params, RETURN_TYPE_STRING);
    }

    /**
     * 获取静态密码
     *
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<String> getStaticPassword(@Nullable IdentityDTO operator,
                                                       String iotId,
                                                       Integer passwordIndex) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_EXT_LOCK, "lockStaticPasswordGet");
        HashMap<String, Object> params = createParamMap(operator, iotId, 4);
        params.put("passwordIndex", passwordIndex);
        return getApiCommand(apiConfig, params, RETURN_TYPE_STRING);
    }

    /**
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<Void> pushStaticPassword(@Nullable IdentityDTO operator,
                                                      String iotId,
                                                      Integer passwordIndex,
                                                      IdentityDTO target) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_EXT_LOCK, "lockStaticPasswordPush");
        HashMap<String, Object> params = createParamMap(operator, iotId, 4);
        params.put("passwordIndex", passwordIndex);
        params.put("target", target);
        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<Void> updateStaticPasswordRule(@Nullable IdentityDTO operator,
                                                            String iotId,
                                                            Integer passwordIndex,
                                                            String ruleData) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_EXT_LOCK, "lockStaticPasswordRuleUpdate");
        HashMap<String, Object> params = createParamMap(operator, iotId, 4);
        params.put("passwordIndex", passwordIndex);
        params.put("ruleData", ruleData);
        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<String> getDynamicPassword(@Nullable IdentityDTO operator,
                                                        String iotId,
                                                        Integer passwordIndex,
                                                        @Nullable Integer stopSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_EXT_LOCK, "lockDynamicPasswordGet");
        HashMap<String, Object> params = createParamMap(operator, iotId, 4);
        params.put("passwordIndex", passwordIndex);
        params.put("stopSize", stopSize);
        return getApiCommand(apiConfig, params, RETURN_TYPE_STRING);
    }

    /**
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<Void> pushDynamicPassword(@Nullable IdentityDTO operator,
                                                       String iotId,
                                                       Integer passwordIndex,
                                                       @Nullable Integer stopSize,
                                                       IdentityDTO target) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_EXT_LOCK, "lockDynamicPasswordPush");
        HashMap<String, Object> params = createParamMap(operator, iotId, 8);
        params.put("passwordIndex", passwordIndex);
        params.put("stopSize", stopSize);
        params.put("target", target);
        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<String> updateDynamicPasswordRule(@Nullable IdentityDTO operator,
                                                               String iotId,
                                                               Integer passwordIndex,
                                                               String ruleData) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_EXT_LOCK, "lockDynamicPasswordRuleUpdate");
        HashMap<String, Object> params = createParamMap(operator, iotId, 4);
        params.put("passwordIndex", passwordIndex);
        params.put("ruleData", ruleData);
        return getApiCommand(apiConfig, params, RETURN_TYPE_STRING);
    }

    /**
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<Void> lock(@Nullable IdentityDTO operator,
                                        String iotId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_EXT_LOCK, "lockLock");
        HashMap<String, Object> params = createParamMap(operator, iotId, 2);
        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<Void> unlock(@Nullable IdentityDTO operator,
                                          String iotId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_EXT_LOCK, "lockUnlock");
        HashMap<String, Object> params = createParamMap(operator, iotId, 2);
        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

}
