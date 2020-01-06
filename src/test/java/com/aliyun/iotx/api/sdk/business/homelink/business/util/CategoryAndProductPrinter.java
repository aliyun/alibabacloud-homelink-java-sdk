package com.aliyun.iotx.api.sdk.business.homelink.business.util;

import com.aliyun.iotx.api.sdk.business.homelink.business.ProductApi;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.CategoryDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.product.ProductDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;


/**
 * 打印产品、品类
 *
 * @date 2019/09/20
 */
public class CategoryAndProductPrinter {

    public static void printAllCategoryAndProductList(IdentityDTO operator) {

        PageDTO<CategoryDTO> page;

        List<CategoryDTO> total = Lists.newLinkedList();

        int pageNo = 1;
        while (true) {

            page = ProductApi.queryCategories(operator, null, null, pageNo++, 100)
                .executeAndGet();
            if (page.getData().isEmpty()) {
                break;
            }

            total.addAll(page.getData());
        }

        System.out.println("total: " + total.size());

        List<CategoryDTO> root = Lists.newLinkedList();
        Map<Long, List<CategoryDTO>> map = Maps.newHashMap();

        for (CategoryDTO dto : total) {
            if (dto.getState() == 0) {
                continue;
            }

            if (isNull(dto.getSuperId()) || dto.getSuperId().equals(0L)) {
                root.add(dto);
            } else {
                List<CategoryDTO> mapList = map.computeIfAbsent(dto.getSuperId(), key -> Lists.newLinkedList());
                mapList.add(dto);
            }
        }

        root.sort((o1, o2) -> (int)(o1.getCategoryId() - o2.getCategoryId()));

        for (CategoryDTO cat : root) {
            printCategory(cat, 0);
            dumpCategory(map, cat.getCategoryId(), 1, operator);
        }
    }

    private static void dumpCategory(Map<Long, List<CategoryDTO>> map,
                                     Long parentId,
                                     int deep, IdentityDTO operator) {

        if (!map.containsKey(parentId)) {
            return;
        }

        List<CategoryDTO> categoryDTOS = map.get(parentId);
        categoryDTOS.sort((o1, o2) -> (int)(o1.getCategoryId() - o2.getCategoryId()));
        for (CategoryDTO categoryDTO : categoryDTOS) {
            printCategory(categoryDTO, deep + 1);
            dumpCategory(map, categoryDTO.getCategoryId(), deep + 1, operator);
            List<ProductDTO> allProduct = queryAllProduct(categoryDTO.getCategoryKey(), operator);
            if (!allProduct.isEmpty()) {
                allProduct.forEach(prd -> printProduct(prd, deep));
            }
        }
    }

    private static void printCategory(CategoryDTO dto, int deep) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < deep; i++) {
            sb.append("    ");
        }
        System.out.println(
            dto.getCategoryId() + "\t" + sb.toString() + dto.getCategoryName() + ", " + dto.getCategoryKey() + ", "
                + dto.getImageUrl());
    }

    private static void printProduct(ProductDTO dto, int deep) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < deep; i++) {
            sb.append("    ");
        }
        sb.append("    ");
        sb.append("    ");
        System.out.println(sb.toString() + dto.getProductName() + ", " + dto.getProductKey());
    }

    private static List<ProductDTO> queryAllProduct(String categoryKey,
                                                    IdentityDTO operator) {
        int pageNo = 1;
        int pageSiz = 100;

        List<ProductDTO> total = Lists.newLinkedList();
        while (true) {
            PageDTO<ProductDTO> products = ProductApi.queryProducts(operator, null, null, categoryKey,
                pageNo++, pageSiz).executeAndGet();
            List<ProductDTO> list = products.getData();
            if (isNull(list) || list.isEmpty()) {
                break;
            }
            total.addAll(list);
        }
        return total;
    }
}
