package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.alibaba.fastjson.JSON;
import com.aliyun.iotx.api.sdk.TestInit;
import com.aliyun.iotx.api.sdk.business.homelink.business.util.CategoryAndProductPrinter;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.CategoryDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.product.ProductDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @date 2019/08/27
 */
@Slf4j
public class ProductApiTest {

    private static IdentityDTO operator;

    @Before
    public void setUp() {
        TestInit.init();
        operator = TestInit.getOperator();
    }

    @Test
    public void listCategory() {
        PageDTO<CategoryDTO> categories = ProductApi.queryCategories(operator, null, null, 1, 100)
            .executeAndGet();

        assertThat(categories).isNotNull();
        assertThat(categories.getData()).isNotEmpty();

        System.out.println(JSON.toJSONString(categories, true));
    }

    @Test
    public void getProductList() {
        PageDTO<ProductDTO> products = ProductApi.queryProducts(operator, null, null, null, 1, 20)
            .executeAndGet();

        assertThat(products).isNotNull();
        assertThat(products.getData()).isNotEmpty();

        System.out.println(JSON.toJSONString(products, true));
    }

    @Test
    public void getProductListByCategoryKey() {
        PageDTO<ProductDTO> products = ProductApi.queryProducts(operator, null, null, "SmartLife", 1, 20)
            .executeAndGet();

        assertThat(products).isNotNull();
        //assertThat(products.getData()).isNotEmpty();

        System.out.println(JSON.toJSONString(products, true));
    }

    @Test
    public void getProductListByCategory() {
        String categoryKey = "AirConditioning";

        PageDTO<ProductDTO> products = ProductApi.queryProducts(operator, null, null, categoryKey, 1, 20)
            .executeAndGet();

        assertThat(products).isNotNull();
        List<ProductDTO> data = products.getData();
        assertThat(data).isNotEmpty();

        for (ProductDTO datum : data) {
            assertThat(datum.getCategoryKey()).isEqualTo(categoryKey);
        }

        System.out.println(JSON.toJSONString(products, true));
    }

    @Test
    public void getProductListByCategoryLighting() {
        String categoryKey = "Lighting";

        PageDTO<ProductDTO> products = ProductApi.queryProducts(operator, null, null, categoryKey, 1, 20)
            .executeAndGet();

        assertThat(products).isNotNull();
        List<ProductDTO> data = products.getData();
        assertThat(data).isNotEmpty();

        for (ProductDTO datum : data) {
            assertThat(datum.getCategoryKey()).isEqualTo(categoryKey);
        }

        System.out.println(JSON.toJSONString(products, true));
    }

    @Test
    public void getProductListByProductKey() {
        String productKey = "a1W1MW4xdB9";
        PageDTO<ProductDTO> products = ProductApi.queryProducts(operator, productKey, null, null, 1, 20)
            .executeAndGet();
        assertThat(products).isNotNull();

        List<ProductDTO> data = products.getData();
        assertThat(data).isNotEmpty();

        for (ProductDTO datum : data) {
            assertThat(datum.getProductKey()).isEqualTo(productKey);
        }

        System.out.println(JSON.toJSONString(products, true));
    }

    @Test
    public void getProductListByProductName() {
        String productName = "空调机";
        PageDTO<ProductDTO> products = ProductApi.queryProducts(operator, null, productName, null, 1, 20)
            .executeAndGet();

        assertThat(products).isNotNull();

        List<ProductDTO> data = products.getData();
        assertThat(data).isNotEmpty();

        for (ProductDTO datum : data) {
            assertThat(datum.getProductName()).isEqualTo(productName);
        }

        System.out.println(JSON.toJSONString(products, true));
    }

    @Test
    public void printAllCategoryAndProductList() {
        CategoryAndProductPrinter.printAllCategoryAndProductList(operator);
    }

}