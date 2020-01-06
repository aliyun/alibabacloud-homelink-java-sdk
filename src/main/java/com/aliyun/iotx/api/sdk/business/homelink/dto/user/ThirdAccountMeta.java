package com.aliyun.iotx.api.sdk.business.homelink.dto.user;

import java.io.Serializable;

/**
 * @author qianxing.hp
 * @date 19-01-25.
 */
public class ThirdAccountMeta implements Serializable {

    private static final long serialVersionUID = 6922655849689885688L;


    /**
     * 三方账号的openId
     */
    private String openId;

    /**
     * 三方账号类型
     *
     */
    private Integer openIdType;

    /**
     * iot内部的账号id
     */
    private String accountId;

    /**
     * 用户登录名
     */
    private String loginName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 创建者
     */
    private String creater;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 密码
     */
    private String password;

    /**
     * 当前 loginPwd 是否已经加密,默认是false
     */
    private Boolean pwdEncrypted = false;



    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPwdEncrypted() {
        return pwdEncrypted;
    }

    public void setPwdEncrypted(Boolean pwdEncrypted) {
        this.pwdEncrypted = pwdEncrypted;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getOpenIdType() {
        return openIdType;
    }

    public void setOpenIdType(Integer openIdType) {
        this.openIdType = openIdType;
    }
}
