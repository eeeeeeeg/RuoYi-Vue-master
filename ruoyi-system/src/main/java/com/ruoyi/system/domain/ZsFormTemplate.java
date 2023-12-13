package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * formTemplate对象 zs_form_template
 * 
 * @author ruoyi
 * @date 2023-12-10
 */
public class ZsFormTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表单模板名称 */
    private Long id;

    /** 表单模板名字 */
    @Excel(name = "表单模板名字")
    private String name;

    /** 表单模板内容 */
    @Excel(name = "表单模板内容")
    private String formData;

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
    public void setFormData(String formData) 
    {
        this.formData = formData;
    }

    public String getFormData() 
    {
        return formData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("formData", getFormData())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .toString();
    }
}
