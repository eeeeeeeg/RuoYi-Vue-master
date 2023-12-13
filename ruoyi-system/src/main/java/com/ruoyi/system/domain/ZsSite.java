package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 站点维护
对象 zs_site
 * 
 * @author ruoyi
 * @date 2023-12-06
 */
public class ZsSite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 站点id
 */
    private Long id;

    /** 站点名称
站点名称 */
    @Excel(name = "站点名称")
    private String name;

    /** 站点坐标 */
    @Excel(name = "站点坐标")
    private String coordinates;

    /** 站点状态枚举
01 正常
02 作废 */
    @Excel(name = "站点状态枚举")
    private String status;

    /** 创建人 
用户id */
    @Excel(name = "创建人")
    private Long createUser;

    /** 更新人 
用户id */
    @Excel(name = "更新人")
    private Long updateUser;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCoordinates(String coordinates) 
    {
        this.coordinates = coordinates;
    }

    public String getCoordinates() 
    {
        return coordinates;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setCreateUser(Long createUser)
    {
        this.createUser = createUser;
    }

    public Long getCreateUser()
    {
        return createUser;
    }
    public void setUpdateUser(Long updateUser)
    {
        this.updateUser = updateUser;
    }

    public Long getUpdateUser()
    {
        return updateUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("coordinates", getCoordinates())
            .append("status", getStatus())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
