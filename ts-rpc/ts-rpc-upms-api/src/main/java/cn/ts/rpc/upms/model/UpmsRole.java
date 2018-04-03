package cn.ts.rpc.upms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *   upms系统-角色表（created by yl on 2017-07-18）
 * 
 * <pre>
 * <table>
 *  <tr><td>属性名</td><td>数据类型</td><td>表字段名</td><td>表字段描述</td><td>默认值</td></tr>
 *  <tr><td>name</td><td>String</td><td>NAME</td><td>角色名称</td><td></td></tr>
 *  <tr><td>title</td><td>String</td><td>TITLE</td><td>角色标题</td><td></td></tr>
 *  <tr><td>description</td><td>String</td><td>DESCRIPTION</td><td>角色描述</td><td></td></tr>
 *  <tr><td>createTime</td><td>Date</td><td>CREATE_TIME</td><td>创建时间</td><td></td></tr>
 *  <tr><td>orders</td><td>BigDecimal</td><td>ORDERS</td><td>排序</td><td></td></tr>
 * </table>
 * </pre>
 * 
 * @author Created by Mybatis Generator on 2017/07/21.
 */
@Table(name = "UPMS_ROLE")
public class UpmsRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    private String name;

    private String title;

    private String description;

    private Date createTime;

    private BigDecimal orders;

    private static final long serialVersionUID = 1L;

    public UpmsRole(BigDecimal id, String name, String title, String description, Date createTime, BigDecimal orders) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.createTime = createTime;
        this.orders = orders;
    }

    public UpmsRole() {
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
        sb.append(", createTime=").append(createTime);
        sb.append(", orders=").append(orders);
        sb.append("]");
        return sb.toString();
    }
}