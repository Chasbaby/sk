package com.ruoyi.page.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 底部展示对象 sys_footer
 * 
 * @author ruoyi
 * @date 2023-02-03
 */
public class SysFooter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** footerId */
    private Long footerId;

    /** 标签 */
    @Excel(name = "标签")
    private String footerLabel;

    /** footer区展示类型(社区链接) */
    @Excel(name = "footer区展示类型(社区链接)")
    private Integer footerType;

    /** 是否开启 */
    @Excel(name = "是否开启")
    private String flag;

    @Excel(name = "对外类型")
    /**对外展示*/
    private String footerShow;
    /** 如果只是展示内容直接写在这里 */
    private String footerContent;

    /** 如果展示的需要路由那么放在这里 */
    private String footerRoute;

    /** 组件位置 */
    private String footerComponent;

    /** 链接路径 */
    private String footerSrc;

    public String getFooterShow() {
        return footerShow;
    }

    public void setFooterShow(String footerShow) {
        this.footerShow = footerShow;
    }

    public void setFooterId(Long footerId)
    {
        this.footerId = footerId;
    }

    public Long getFooterId() 
    {
        return footerId;
    }
    public void setFooterLabel(String footerLabel) 
    {
        this.footerLabel = footerLabel;
    }

    public String getFooterLabel() 
    {
        return footerLabel;
    }
    public void setFooterType(Integer footerType) 
    {
        this.footerType = footerType;
    }

    public Integer getFooterType() 
    {
        return footerType;
    }
    public void setFlag(String flag) 
    {
        this.flag = flag;
    }

    public String getFlag() 
    {
        return flag;
    }
    public void setFooterContent(String footerContent) 
    {
        this.footerContent = footerContent;
    }

    public String getFooterContent() 
    {
        return footerContent;
    }
    public void setFooterRoute(String footerRoute) 
    {
        this.footerRoute = footerRoute;
    }

    public String getFooterRoute() 
    {
        return footerRoute;
    }
    public void setFooterComponent(String footerComponent) 
    {
        this.footerComponent = footerComponent;
    }

    public String getFooterComponent() 
    {
        return footerComponent;
    }
    public void setFooterSrc(String footerSrc) 
    {
        this.footerSrc = footerSrc;
    }

    public String getFooterSrc() 
    {
        return footerSrc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("footerId", getFooterId())
            .append("footerLabel", getFooterLabel())
            .append("footerType", getFooterType())
            .append("footerShow",getFooterShow())
            .append("flag", getFlag())
            .append("footerContent", getFooterContent())
            .append("footerRoute", getFooterRoute())
            .append("footerComponent", getFooterComponent())
            .append("footerSrc", getFooterSrc())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
