package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.CategoryDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.product.ProductDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.product.ProductQueryDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.command.ApiCommand;
import com.google.common.base.Strings;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Objects;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.G_HOME_LINK;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.RETURN_TYPE_GET_TSL;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiUtil.createParamMap;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;


/**
 * 产品、品类相关API
 *
 * @date 2019/07/09
 */
@SuppressWarnings("WeakerAccess")
public class ProductApi {

    /**
     * 品类查询
     *
     * @param operator         操作员
     * @param categoryNameLike 品类名称，支持模糊查询
     * @param superId          上级ID
     * @param pageNo           页码
     * @param pageSize         页大小
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行
     */
    public static ApiCommand<PageDTO<CategoryDTO>> queryCategories(IdentityDTO operator,
                                                                   @Nullable String categoryNameLike,
                                                                   @Nullable Long superId,
                                                                   int pageNo,
                                                                   int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_category_list");

        HashMap<String, Object> params = createParamMap(operator, 8);
        if (Strings.isNullOrEmpty(categoryNameLike)) {
            params.put("categoryName", categoryNameLike);
        }
        if (Objects.nonNull(superId)) {
            params.put("superId", superId);
        }
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);

        return getApiCommand(apiConfig, params, ApiResultTypeConstants.RETURN_TYPE_CATEGORY_PAGE);
    }

    /**
     * 查询产品列表
     *
     * @param operator    操作员
     * @param productName PK
     * @param categoryKey DN
     * @param pageNo      页码（min=1）
     * @param pageSize    页大小（min=1, max=100)
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行
     */
    public static ApiCommand<PageDTO<ProductDTO>> queryProducts(IdentityDTO operator,
                                                                String productKey,
                                                                String productName,
                                                                String categoryKey,
                                                                int pageNo,
                                                                int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "product_list");

        ProductQueryDTO query = new ProductQueryDTO();
        query.setProductKey(productKey);
        query.setProductName(productName);
        query.setCategoryKey(categoryKey);
        query.setPageNo(pageNo);
        query.setPageSize(pageSize);

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("productQuery", query);

        return getApiCommand(apiConfig, params, ApiResultTypeConstants.RETURN_TYPE_PRODUCT_LIST);
    }

    /**
     * 获取产品TSL
     *
     * @param productKey
     * @param deviceName
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行
     */
    public static ApiCommand<JSONObject> getProductTsl(String productKey, String deviceName) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "product_tsl_get");

        HashMap<String, Object> params = new HashMap<>(2);
        params.put("productKey", productKey);
        params.put("deviceName", deviceName);

        return getApiCommand(apiConfig, params, RETURN_TYPE_GET_TSL);
    }

}
