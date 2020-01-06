package com.aliyun.iotx.api.sdk.business.external;

import com.aliyun.iotx.api.sdk.TestInit;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import org.junit.Before;
import org.junit.Test;


/**
 * @date 2019/10/31
 */
public class LockApiTest {

    private static IdentityDTO operator;

    @Before
    public void setUp() {
        TestInit.init();
        operator = TestInit.getOperator();
    }

    @Test
    public void createStaticPassword() {

        LockApi
            .createStaticPassword(null, "abc", 1, "pwd", "rule")
            .executeAndGet();
    }

    @Test
    public void updateStaticPassword() {
        LockApi
            .updateStaticPassword(null, "abc", 1, "newPwd", "oldPwd", "rule")
            .executeAndGet();
    }

    @Test
    public void getStaticPassword() {
        LockApi.getStaticPassword(null, "abc", 1).executeAndGet();
    }

    @Test
    public void pushStaticPassword() {
        LockApi.pushStaticPassword(null, "abc", 1, operator).execute();
    }

    @Test
    public void updateStaticPasswordRule() {
        LockApi.updateStaticPasswordRule(null, "abc", 1, "rule").execute();
    }

    @Test
    public void getDynamicPassword() {
        LockApi.getDynamicPassword(null, "abc", 1, 1).executeAndGet();
    }

    @Test
    public void pushDynamicPassword() {
        LockApi.pushDynamicPassword(null, "abc", 1, 1, operator).execute();
    }

    @Test
    public void updateDynamicPasswordRule() {
        LockApi.updateDynamicPasswordRule(null, "bc", 1, "rule").executeAndGet();
    }

    @Test
    public void lock() {
        LockApi.lock(null, "abc").execute();
    }

    @Test
    public void unlock() {
        LockApi.unlock(null, "abc").execute();
    }
}