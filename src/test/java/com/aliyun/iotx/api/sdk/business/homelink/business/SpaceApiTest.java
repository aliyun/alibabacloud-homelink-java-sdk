/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.aliyun.iotx.api.sdk.TestInit;
import com.aliyun.iotx.api.sdk.business.homelink.business.util.SpacePrinter;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import org.junit.Before;
import org.junit.Test;


/**
 * @author alibaba
 * @date 2019/09/20
 */
public class SpaceApiTest {

    private static IdentityDTO operator;

    @Before
    public void setUp() {
        TestInit.init();
        operator = TestInit.getOperator();
    }

    @Test
    public void createRootSpace() {
    }

    @Test
    public void createSubSpace() {
    }

    @Test
    public void deleteSpace() {
    }

    @Test
    public void updateSpace() {
    }

    @Test
    public void getSpace() {
    }

    @Test
    public void getSubSpace() {
    }

    @Test
    public void getRootSpace() {
    }

    @Test
    public void printTenantSpaces() {
        SpacePrinter.dumpTenantSpaces(operator);
    }
}