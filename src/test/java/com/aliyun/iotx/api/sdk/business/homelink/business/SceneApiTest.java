/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.aliyun.iotx.api.sdk.TestInit;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import org.junit.Before;
import org.junit.Test;


/**
 * @author alibaba
 * @date 2019/09/20
 */
public class SceneApiTest {

    private static IdentityDTO operator;

    @Before
    public void setUp() {
        TestInit.init();
        operator = TestInit.getOperator();
    }

    @Test
    public void createScene() {
    }

    @Test
    public void getScene() {
    }

    @Test
    public void deleteScene() {
    }

    @Test
    public void queryScene() {
    }

    @Test
    public void runScene() {
    }

    @Test
    public void updateSceneBase() {
    }

    @Test
    public void updateSceneSwitch() {
    }

    @Test
    public void updateSceneTca() {
    }
}