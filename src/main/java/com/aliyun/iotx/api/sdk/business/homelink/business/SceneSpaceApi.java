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
import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.G_HOME_LINK;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.RETURN_TYPE_VOID;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.RETURN_TYPE_SCENE_DESIGN_PAGE;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;


/**
 * @date 2019/07/25
 */
public class SceneSpaceApi {

    /**
     * 绑定空间场景
     *
     * @param operator    操作人
     * @param spaceId     空间ID
     * @param sceneIdList 场景ID列表
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    @SuppressWarnings("Duplicates")
    public static ApiCommand<Void> bindSpaceScene(IdentityDTO operator,
                                                  String spaceId,
                                                  List<String> sceneIdList) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_scene_bind");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("spaceId", spaceId);
        params.put("sceneIdList", sceneIdList);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 查询空间场景
     *
     * @param operator 操作人
     * @param spaceId  空间ID
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取场景信息分页
     */
    public static ApiCommand<PageDTO<SceneDesignDTO>> querySpaceScenes(IdentityDTO operator,
                                                                    String spaceId,
                                                                    String name,
                                                                    int pageNo,
                                                                    int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_scene_list");

        SceneSearchDTO searchInfo = new SceneSearchDTO();
        searchInfo.setName(name);
        searchInfo.setPageNo(pageNo);
        searchInfo.setPageSize(pageSize);

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("spaceId", spaceId);
        params.put("searchInfo", searchInfo);

        return getApiCommand(apiConfig, params, RETURN_TYPE_SCENE_DESIGN_PAGE);
    }

    /**
     * 解绑空间场景
     *
     * @param operator 操作人
     * @param spaceId  空间ID
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    @SuppressWarnings("Duplicates")
    public static ApiCommand<Void> unbindSpaceScene(IdentityDTO operator, String spaceId, List<String> sceneIdList) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_scene_unbind");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("spaceId", spaceId);
        params.put("sceneIdList", sceneIdList);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 解绑空间所有场景
     *
     * @param operator 操作人
     * @param spaceId  空间ID
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Void> unbindSpaceAllScene(IdentityDTO operator, String spaceId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_allscene_unbind");
        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("spaceId", spaceId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

}
