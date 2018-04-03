package cn.ts.rpc.upms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *   upms系统-角色权限关联表（created by yl on 2017-07-18）
 * 
 * <pre>
 * <table>
 *  <tr><td>属性名</td><td>数据类型</td><td>表字段名</td><td>表字段描述</td><td>默认值</td></tr>
 *  <tr><td>roleId</td><td>BigDecimal</td><td>ROLE_ID</td><td>角色表主键</td><td></td></tr>
 *  <tr><td>permissionId</td><td>BigDecimal</td><td>PERMISSION_ID</td><td>权限表主键</td><td></td></tr>
 * </table>
 * </pre>
 * 
 * @author Created by Mybatis Generator on 2017/07/21.
 */
@Table(name = "UPMS_ROLE_PERMISSION")
public class UpmsRolePermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    private BigDecimal roleId;

    private BigDecimal permissionId;

    private static final long serialVersionUID = 1L;

    public UpmsRolePermission(BigDecimal id, BigDecimal roleId, BigDecimal permissionId) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public UpmsRolePermission() {
        super();
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getRoleId() {
        return roleId;
    }

    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }

    public BigDecimal getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(BigDecimal permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", permissionId=").append(permissionId);
        sb.append("]");
        return sb.toString();
    }
}