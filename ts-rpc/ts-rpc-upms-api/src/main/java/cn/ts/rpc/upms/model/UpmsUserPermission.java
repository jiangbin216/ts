package cn.ts.rpc.upms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *   upms系统-用户权限关联表（created by yl on 2017-07-18）
 * 
 * <pre>
 * <table>
 *  <tr><td>属性名</td><td>数据类型</td><td>表字段名</td><td>表字段描述</td><td>默认值</td></tr>
 *  <tr><td>userId</td><td>BigDecimal</td><td>USER_ID</td><td>用户表主键</td><td></td></tr>
 *  <tr><td>permissionId</td><td>BigDecimal</td><td>PERMISSION_ID</td><td>权限表主键</td><td></td></tr>
 *  <tr><td>type</td><td>BigDecimal</td><td>TYPE</td><td>权限类型(-1:减权限,1:增权限)</td><td></td></tr>
 * </table>
 * </pre>
 * 
 * @author Created by Mybatis Generator on 2017/07/21.
 */
@Table(name = "UPMS_USER_PERMISSION")
public class UpmsUserPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    private BigDecimal userId;

    private BigDecimal permissionId;

    private BigDecimal type;

    private static final long serialVersionUID = 1L;

    public UpmsUserPermission(BigDecimal id, BigDecimal userId, BigDecimal permissionId, BigDecimal type) {
        this.id = id;
        this.userId = userId;
        this.permissionId = permissionId;
        this.type = type;
    }

    public UpmsUserPermission() {
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

    public BigDecimal getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(BigDecimal permissionId) {
        this.permissionId = permissionId;
    }

    public BigDecimal getType() {
        return type;
    }

    public void setType(BigDecimal type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", type=").append(type);
        sb.append("]");
        return sb.toString();
    }
}