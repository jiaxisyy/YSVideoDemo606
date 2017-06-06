package com.example.shuangxiang.ysvideodemo.login.bean;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/10.
 */

public class LoginInfo {


    @Override
    public String toString() {
        return "LoginInfo{" +
                "admin=" + admin +
                ", createBy=" + createBy +
                ", createDate=" + createDate +
                ", delFlag='" + delFlag + '\'' +
                ", email='" + email + '\'' +
                ", errorCount=" + errorCount +
                ", id='" + id + '\'' +
                ", isAdmin=" + isAdmin +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                ", lastLoginErrorMsg='" + lastLoginErrorMsg + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", level='" + level + '\'' +
                ", loginDate='" + loginDate + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", nextLoginDate=" + nextLoginDate +
                ", orgId='" + orgId + '\'' +
                ", orgName=" + orgName +
                ", phone='" + phone + '\'' +
                ", remarks='" + remarks + '\'' +
                ", roleId=" + roleId +
                ", roleIds=" + roleIds +
                ", status='" + status + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", username='" + username + '\'' +
                ", userpic='" + userpic + '\'' +
                ", roles=" + roles +
                '}';
    }

    /**
     * admin : true
     * createBy : null
     * createDate : null
     * delFlag : 0
     * email :
     * errorCount : 0
     * id : a0009f2f-31f8-4fac-b3e0-ed42d7d48796
     * isAdmin : true
     * lastLoginDate : 2017-04-10 15:56:38
     * lastLoginErrorMsg : 密码错误
     * lastLoginIp : 223.72.68.163
     * level : TENANT
     * loginDate : 2017-04-10 17:03:45
     * loginIp : 10.199.198.114
     * mobile :
     * name : 道路监控
     * nextLoginDate : null
     * orgId :
     * orgName : null
     * phone :
     * remarks :
     * roleId : null
     * roleIds : null
     * roles : []
     * status : 1
     * tenantId : sw23a5c0a36421cacf34s2zd332973a
     * updateBy : a0009f2f-31f8-4fac-b3e0-ed42d7d48796
     * updateDate : 2017-04-10 17:03:45
     * username : swadmin
     * userpic : 5005e33d97024bd583b44e12ad03d019
     */



    private boolean admin;
    private Object createBy;
    private Object createDate;
    private String delFlag;
    private String email;
    private int errorCount;
    private String id;
    private boolean isAdmin;
    private String lastLoginDate;
    private String lastLoginErrorMsg;
    private String lastLoginIp;
    private String level;
    private String loginDate;
    private String loginIp;
    private String mobile;
    private String name;
    private Object nextLoginDate;
    private String orgId;
    private Object orgName;
    private String phone;
    private String remarks;
    private Object roleId;
    private Object roleIds;
    private String status;
    private String tenantId;
    private String updateBy;
    private String updateDate;
    private String username;
    private String userpic;
    private List<?> roles;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginErrorMsg() {
        return lastLoginErrorMsg;
    }

    public void setLastLoginErrorMsg(String lastLoginErrorMsg) {
        this.lastLoginErrorMsg = lastLoginErrorMsg;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getNextLoginDate() {
        return nextLoginDate;
    }

    public void setNextLoginDate(Object nextLoginDate) {
        this.nextLoginDate = nextLoginDate;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Object getOrgName() {
        return orgName;
    }

    public void setOrgName(Object orgName) {
        this.orgName = orgName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Object getRoleId() {
        return roleId;
    }

    public void setRoleId(Object roleId) {
        this.roleId = roleId;
    }

    public Object getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Object roleIds) {
        this.roleIds = roleIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public List<?> getRoles() {
        return roles;
    }

    public void setRoles(List<?> roles) {
        this.roles = roles;
    }
}
