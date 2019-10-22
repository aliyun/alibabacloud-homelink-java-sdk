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
public class SpaceUserApiTest {

    private static IdentityDTO operator;

    @Before
    public void setUp() {
        TestInit.init();
        operator = TestInit.getOperator();
    }

    @Test
    public void bindUserSpace() {
    }

    @Test
    public void unbindSpaceUsers() {
    }

    @Test
    public void unbindUsersSpace() {
    }

    @Test
    public void unbindUserSpace() {
    }

    @Test
    public void queryUserSpaces() {
    }

    @Test
    public void querySpaceUsers() {
    }
}