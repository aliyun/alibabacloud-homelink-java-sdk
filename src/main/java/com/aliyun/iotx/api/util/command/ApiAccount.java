package com.aliyun.iotx.api.util.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;


/**
 * 账号封装，主要是为{@link ApiCommand}提供账号的iotToken信息。其它参数可以作为测试API的参数用。
 *
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2018/11/1
 */
@Builder
@Getter
@EqualsAndHashCode
public class ApiAccount {

    private String aliYunAccount;

    private String identityId;

    private String tenantId;

    private String iotToken;

    private String appKey;

    private String appSecret;

    private String accessKey;

    private String accessSecret;

}
