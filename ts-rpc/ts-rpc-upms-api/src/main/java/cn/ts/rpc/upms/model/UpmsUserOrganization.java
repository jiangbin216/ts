package cn.ts.rpc.upms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *   upms系统-用户组织关联表（created by yl on 2017-07-18）
 * 
 * <pre>
 * <table>
 *  <tr><td>属性名</td><td>数据类型</td><td>表字段名</td><td>表字段描述</td><td>默认值</td></tr>
 *  <tr><td>userId</td><td>BigDecimal</td><td>USER_ID</td><td>用户表主键</td><td></td></tr>
 *  <tr><td>organizationId</td><td>BigDecimal</td><td>ORGANIZATION_ID</td><td>组织表主键</td><td></td></tr>
 * </table>
 * </pre>
 * 
 * @author Created by Mybatis Generator on 2017/07/21.
 */
@Table(name = "UPMS_USER_ORGANIZATION")
public class UpmsUserOrganization implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    private BigDecimal userId;

    private BigDecimal organizationId;

    private static final long serialVersionUID = 1L;

    public UpmsUserOrganization(BigDecimal id, BigDecimal userId, BigDecimal organizationId) {
        this.id = id;
        this.userId = userId;
        this.organizationId = organizationId;
    }

    public UpmsUserOrganization() {
        super();
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public BigDecimal getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(BigDecimal organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", organizationId=").append(organizationId);
        sb.append("]");
        return sb.toString();
    }
}