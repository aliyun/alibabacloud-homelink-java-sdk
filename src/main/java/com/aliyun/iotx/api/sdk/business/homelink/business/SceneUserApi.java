package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.aliyun.iotx.api.sdk.business.homelink.dto.scene.SceneDesignDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.scene.SceneSearchDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.command.ApiCommand;

import java.util.HashMap;
import java.util.List;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiUtil.createParamMap;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.*;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;


/**
 * 场景与用户相关API
 *
 * @date 2019/07/25
 */
public class SceneUserApi {

    /**
     * 绑定用户和场景
     *
     * @param operator 操作者
     * @param sceneId  场景ID
     * @param target   绑定的用户
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    @SuppressWarnings("Duplicates")
    public static ApiCommand<Void> bindUserScene(IdentityDTO operator,
                                                 String sceneId,
                                                 IdentityDTO target) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "user_scene_bind");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("sceneId", sceneId);
        params.put("target", target);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 解绑用户和场景
     *
     * @param operator 操作者
     * @param sceneId  场景ID
     * @param target   绑定的用户
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    @SuppressWarnings("Duplicates")
    public static ApiCommand<Void> unbindUserScene(IdentityDTO operator, IdentityDTO target, String sceneId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "user_scene_unbind");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("sceneId", sceneId);
        params.put("target", target);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 查询用户的场景
     *
     * @param operator 操作者
     * @param target   绑定的用户
     * @param pageNo   页码
     * @param pageSize 页数
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取分页场景数据
     */
    public static ApiCommand<PageDTO<SceneDesignDTO>> queryUserScenes(IdentityDTO operator,
                                                                      IdentityDTO target,
                                                                      int pageNo,
                                                                      int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "user_scene_list");

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("target", target);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);

        return getApiCommand(apiConfig, params, RETURN_TYPE_SCENE_DESIGN_PAGE);
    }

    /**
     * 查询场景的用户
     *
     * @param operator 操作者
     * @param sceneId  要查询的场景ID
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取用户ID列表
     */
    public static ApiCommand<List<IdentityDTO>> querySceneUsers(IdentityDTO operator, String sceneId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "scene_user_list");

        SceneSearchDTO query = new SceneSearchDTO();
        query.setSceneId(sceneId);

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("queryInfo", query);

        return getApiCommand(apiConfig, params, RETURN_TYPE_IDENTITY_LIST);
    }
}
