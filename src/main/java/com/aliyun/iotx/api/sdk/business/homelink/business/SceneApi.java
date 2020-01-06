package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.iotx.api.sdk.business.homelink.DefaultAssertFunc;
import com.aliyun.iotx.api.sdk.business.homelink.dto.scene.SceneDesignDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.command.ApiCommand;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiUtil.createParamMap;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.*;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;


/**
 * 场景相关API、创建、修改、删除等
 *
 * @date 2019/07/24
 */
@Slf4j
public class SceneApi {

    /**
     * 创建场景
     *
     * @param operator    操作者
     * @param name        场景名称
     * @param icon        场景图标
     * @param description 场景描述
     * @param triggers    场景trigger
     * @param conditions  场景condition
     * @param actions     场景action
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<String> createScene(IdentityDTO operator,
                                                 String name,
                                                 String icon,
                                                 String description,
                                                 JSONArray triggers,
                                                 JSONArray conditions,
                                                 JSONArray actions,
                                                 boolean enable) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "scene_create");

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("name", name);
        params.put("icon", icon);
        params.put("description", description);
        params.put("triggers", triggers);
        params.put("conditions", conditions);
        params.put("actions", actions);
        params.put("enable", enable);

        return new ApiCommand<String>(apiConfig)
            .paramSupplier(() -> params)
            .assertFunc(DefaultAssertFunc::assertIt)
            .convertFunc(json -> {
                JSONObject jo = (JSONObject)json;
                return jo.getString("value");
            });
    }

    /**
     * 查询场景详情
     *
     * @param operator 操作者
     * @param sceneId  场景ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景详情
     */
    public static ApiCommand<SceneDesignDTO> getScene(IdentityDTO operator, String sceneId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "scene_get");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("sceneId", sceneId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_SCENE_DESIGN);
    }

    /**
     * 删除场景
     *
     * @param operator 操作者
     * @param sceneId  场景ID
     * @return 调用 {@link ApiCommand#execute()}执行
     */
    public static ApiCommand<Void> deleteScene(IdentityDTO operator, String sceneId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "scene_delete");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("sceneId", sceneId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 查询场景
     *
     * @param operator 操作者
     * @param pageNo   页码
     * @param pageSize 页数
     * @return 调用{@link ApiCommand#executeAndGet()}执行场景详情
     */
    public static ApiCommand<PageDTO<SceneDesignDTO>> queryScene(IdentityDTO operator,
                                                                 int pageNo,
                                                                 int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "scene_list");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);

        return getApiCommand(apiConfig, params, RETURN_TYPE_SCENE_DESIGN_PAGE);
    }

    /**
     * 手动触发一个场景
     *
     * @param operator 操作者
     * @param sceneId  场景ID
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Void> runScene(IdentityDTO operator, String sceneId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "scene_instance_run");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("sceneId", sceneId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 修改场景基本信息
     *
     * @param operator    操作者
     * @param name        场景名称
     * @param icon        场景图标
     * @param description 场景描述
     * @param sceneId     场景ID
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Void> updateSceneBase(IdentityDTO operator, String name, String icon, String description,
                                                   String sceneId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "scene_baseinfo_update");

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("name", name);
        params.put("icon", icon);
        params.put("description", description);
        params.put("sceneId", sceneId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 部署/撤销场景
     *
     * @param operator 操作者
     * @param enable   true 部署场景 false 撤销部署
     * @param sceneId  场景ID
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Void> updateSceneSwitch(IdentityDTO operator, Boolean enable, String sceneId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "scene_switch_update");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("sceneId", sceneId);
        params.put("enable", enable);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 更新场景TCA
     *
     * @param operator   操作者
     * @param sceneId    场景ID
     * @param triggers   场景trigger
     * @param conditions 场景conditions
     * @param actions    场景actions
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Void> updateSceneTca(IdentityDTO operator,
                                                  String sceneId,
                                                  JSONArray triggers,
                                                  JSONArray conditions,
                                                  JSONArray actions) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "scene_tca_update");

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("sceneId", sceneId);
        params.put("triggers", triggers);
        params.put("conditions", conditions);
        params.put("actions", actions);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

}
