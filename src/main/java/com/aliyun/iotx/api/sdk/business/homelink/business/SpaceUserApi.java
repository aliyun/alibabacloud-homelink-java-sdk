package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.aliyun.iotx.api.sdk.business.homelink.dto.space.SpaceQueryDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.user.UserDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.command.ApiCommand;

import java.util.HashMap;
import java.util.List;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.*;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiUtil.createParamMap;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;


/**
 * 空间与用户相关API
 *
 * @date 2019/07/25
 */
@SuppressWarnings("WeakerAccess")
public class SpaceUserApi {

    /**
     * 绑定用户跟空间
     *
     * @param operator   操作人
     * @param targetList 要绑定的用户列表
     * @param spaceId    空间ID
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Void> bindUserSpace(IdentityDTO operator,
                                                 List<IdentityDTO> targetList,
                                                 String spaceId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "user_space_bind");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("targetList", targetList);
        params.put("spaceId", spaceId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 解绑空间的所有用户
     *
     * @param operator 操作人
     * @param spaceId  空间ID
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Void> unbindSpaceUsers(IdentityDTO operator, String spaceId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_allusers_unbind");

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("spaceId", spaceId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 解绑用户的所有空间
     *
     * @param operator 操作人
     * @param target   绑定用户信息
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Void> unbindUsersSpace(IdentityDTO operator, IdentityDTO target) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "user_allspaces_unbind");

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("target", target);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 解绑用户跟空间
     *
     * @param operator 操作人
     * @param target   要绑定的用户
     * @param spaceId  空间ID
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Void> unbindUserSpace(IdentityDTO operator,
                                                   IdentityDTO target,
                                                   String spaceId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "user_space_unbind");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("target", target);
        params.put("spaceId", spaceId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 查询用户的空间
     *
     * @param operator 操作人
     * @param target   绑定用户信息
     * @param pageNo   pageNo
     * @param pageSize pageSize
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取空间ID分页
     */
    @SuppressWarnings("Duplicates")
    public static ApiCommand<PageDTO<String>> queryUserSpaces(IdentityDTO operator,
                                                              IdentityDTO target,
                                                              int pageNo,
                                                              int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "user_space_list");

        SpaceQueryDTO query = new SpaceQueryDTO();
        query.setPageNo(pageNo);
        query.setPageSize(pageSize);

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("target", target);
        params.put("userSpaceQuery", query);

        return getApiCommand(apiConfig, params, RETURN_TYPE_STRING_PAGE);
    }

    /**
     * 查询空间的用户
     *
     * @param operator 操作人
     * @param spaceId  空间ID
     * @param pageNo   pageNo
     * @param pageSize pageSize
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取用户信息分页
     */
    @SuppressWarnings("Duplicates")
    public static ApiCommand<PageDTO<IdentityDTO>> querySpaceUsers(IdentityDTO operator,
                                                               String spaceId,
                                                               int pageNo,
                                                               int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_user_list");

        SpaceQueryDTO query = new SpaceQueryDTO();
        query.setPageNo(pageNo);
        query.setPageSize(pageSize);
        query.setSpaceId(spaceId);

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("userSpaceQuery", query);

        return getApiCommand(apiConfig, params, RETURN_TYPE_IDENTITY_PAGE);
    }

}
