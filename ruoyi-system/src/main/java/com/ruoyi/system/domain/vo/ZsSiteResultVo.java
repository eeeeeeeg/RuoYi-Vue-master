package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;

public class ZsSiteResultVo {
    private Long id;

    /** 站点名称
     站点名称 */
    private String name;

    /** 站点坐标 */
    private String coordinates;

    /** 站点状态枚举
     01 正常
     02 作废 */
    private String status;

    /** 创建人
     用户id */
    private Long createUser;
    /** 创建人
     用户姓名 */
    private String createUserName;


    /** 更新人
     用户id */
    private Long updateUser;
    /** 更新人
     用户姓名 */
    private String updateUserName;


    /** 创建时间 */
    private String createTime;
    /** 更新时间 */
    private String updateTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
