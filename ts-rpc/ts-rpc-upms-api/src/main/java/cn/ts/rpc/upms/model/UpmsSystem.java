package cn.ts.rpc.upms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *   upms系统-系统表（created by yl on 2017-07-18）
 * 
 * <pre>
 * <table>
 *  <tr><td>属性名</td><td>数据类型</td><td>表字段名</td><td>表字段描述</td><td>默认值</td></tr>
 *  <tr><td>name</td><td>String</td><td>NAME</td><td>系统名称</td><td></td></tr>
 *  <tr><td>title</td><td>String</td><td>TITLE</td><td>系统标题</td><td></td></tr>
 *  <tr><td>description</td><td>String</td><td>DESCRIPTION</td><td>系统描述</td><td></td></tr>
 *  <tr><td>icon</td><td>String</td><td>ICON</td><td>系统图标</td><td></td></tr>
 *  <tr><td>theme</td><td>String</td><td>THEME</td><td>系统主题</td><td></td></tr>
 *  <tr><td>basepath</td><td>String</td><td>BASEPATH</td><td>系统跟目录</td><td></td></tr>
 *  <tr><td>status</td><td>BigDecimal</td><td>STATUS</td><td>系统状态(-1:黑名单,1:正常</td><td>1
</td></tr>
 *  <tr><td>createTime</td><td>Date</td><td>CREATE_TIME</td><td>创建时间</td><td>sysdate
</td></tr>
 *  <tr><td>orders</td><td>BigDecimal</td><td>ORDERS</td><td>排序</td><td></td></tr>
 * </table>
 * </pre>
 * 
 * @author Created by Mybatis Generator on 2017/07/21.
 */
@Table(name = "UPMS_SYSTEM")
public class UpmsSystem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    private String name;

    private String title;

    private String description;

    private String icon;

    private String theme;

    private String basepath;

    private BigDecimal status;

    private Date createTime;

    private BigDecimal orders;

    private static final long serialVersionUID = 1L;

    public UpmsSystem(BigDecimal id, String name, String title, String description, String icon, String theme, String basepath, BigDecimal status, Date createTime, BigDecimal orders) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.theme = theme;
        this.basepath = basepath;
        this.status = status;
        this.createTime = createTime;
        this.orders = orders;
    }

    public UpmsSystem() {
        super();
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getBasepath() {
        return basepath;
    }

    public void setBasepath(String basepath) {
        this.basepath = basepath;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getOrders() {
        return orders;
    }

    public void setOrders(BigDecimal orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", icon=").append(icon);
        sb.append(", theme=").append(theme);
        sb.append(", basepath=").append(basepath);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", orders=").append(orders);
        sb.append("]");
        return sb.toString();
    }
}