package com.aliyun.iotx.api.sdk.business.homelink.dto.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @author niki
 * @date 17-7-5.
 */
public class IdentityDTO implements Serializable {

    private static final long serialVersionUID = 888315377489939476L;

    private String identityId;

    private Date gmtCreate;

    private Date gmtModified;

    private String tenantId;

    private String loginId;

    private String loginName;

    private String loginSource;

    private String phone;

    private String email;

    private String nickName;

    //private String oauthType;
    //
    private String creater;

    private String modifier;

    private String gender;

    private Date lastLoginTime;

    private String avatarUrl;

    /**
     * 限制子账号创建数量
     */
    private String subIdentityLimitCode;

    private String status;

    /**
     * 账号登录态是否在线: 1-在线；0-离线
     */
    private Integer onlineStatus;

    /**
     * 创建者id
     */
    private String createrIdentityId;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(String loginSource) {
        this.loginSource = loginSource;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    //public String getOauthType() {
    //    return oauthType;
    //}
    //
    //public void setOauthType(String oauthType) {
    //    this.oauthType = oauthType;
    //}

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSubIdentityLimitCode() {
        return subIdentityLimitCode;
    }

    public void setSubIdentityLimitCode(String subIdentityLimitCode) {
        this.subIdentityLimitCode = subIdentityLimitCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    @Override
    public String toString() {
        return "IdentityDTO{" +
            "identityId='" + identityId + '\'' +
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
            ", tenantId='" + tenantId + '\'' +
            ", loginId='" + loginId + '\'' +
            ", loginName='" + loginName + '\'' +
            ", loginSource='" + loginSource + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", nickName='" + nickName + '\'' +
            ", creater='" + creater + '\'' +
            ", modifier='" + modifier + '\'' +
            ", gender='" + gender + '\'' +
            ", lastLoginTime=" + lastLoginTime +
            ", avatarUrl='" + avatarUrl + '\'' +
            ", onlineStatus=" + onlineStatus +
            '}';
    }


    public String getCreaterIdentityId() {
        return createrIdentityId;
    }

    public void setCreaterIdentityId(String createrIdentityId) {
        this.createrIdentityId = createrIdentityId;
    }
}
