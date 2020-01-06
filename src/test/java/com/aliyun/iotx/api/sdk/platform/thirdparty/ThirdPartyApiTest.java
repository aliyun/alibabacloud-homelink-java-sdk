package com.aliyun.iotx.api.sdk.platform.thirdparty;

import com.aliyun.iotx.api.sdk.TestInit;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @date 2019/07/30
 */
public class ThirdPartyApiTest {

    @Before
    public void setUp() {
        TestInit.init();
    }

    @Test
    public void importAccountForThirdParty() {

        // 导入三方账号
        String identityId = ThirdPartyApi.importAccountForThirdParty(
            "test.openId.1",
            "nickName",
            "13900001111",
            "13900001111@139.com",
            null,
            "creator",
            "loginName",
            null,
            false,
            2
        ).executeAndGet();
        assertThat(identityId).isNotBlank();

        System.out.println("identityId: " + identityId);
    }

    @Test
    public void checkAccountRegistered() {
        Boolean registered;

        registered = ThirdPartyApi.checkAccountRegistered("13900001111", null).executeAndGet();
        assertThat(registered).isTrue();

        registered = ThirdPartyApi.checkAccountRegistered("13911110000", null).executeAndGet();
        assertThat(registered).isFalse();
    }
}