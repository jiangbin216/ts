package cn.ts.rpc.upms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *   upms系统-权限表（created by yl on 2017-07-18）
 * 
 * <pre>
 * <table>
 *  <tr><td>属性名</td><td>数据类型</td><td>表字段名</td><td>表字段描述</td><td>默认值</td></tr>
 *  <tr><td>systemId</td><td>BigDecimal</td><td>SYSTEM_ID</td><td>所属系统</td><td></td></tr>
 *  <tr><td>pid</td><td>BigDecimal</td><td>PID</td><td>所属上级</td><td></td></tr>
 *  <tr><td>name</td><td>String</td><td>NAME</td><td>名称</td><td></td></tr>
 *  <tr><td>type</td><td>BigDecimal</td><td>TYPE</td><td>类型(1:目录,2:菜单,3:按钮)</td><td></td></tr>
 *  <tr><td>permissionValue</td><td>String</td><td>PERMISSION_VALUE</td><td>权限值</td><td></td></tr>
 *  <tr><td>uri</td><td>String</td><td>URI</td><td>路径</td><td></td></tr>
 *  <tr><td>icon</td><td>String</td><td>ICON</td><td>图标</td><td></td></tr>
 *  <tr><td>status</td><td>BigDecimal</td><td>STATUS</td><td>状态(0:禁止,1:正常)</td><td>1
</td></tr>
 *  <tr><td>createTime</td><td>Date</td><td>CREATE_TIME</td><td>创建时间</td><td>sysdate
</td></tr>
 *  <tr><td>orders</td><td>BigDecimal</td><td>ORDERS</td><td>排序</td><td></td></tr>
 * </table>
 * </pre>
 * 
 * @author Created by Mybatis Generator on 2017/07/21.
 */
@Table(name = "UPMS_PERMISSION")
public class UpmsPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    private BigDecimal systemId;

    private BigDecimal pid;

    private String name;

    private BigDecimal type;

    private String permissionValue;

    private String uri;

    private String icon;

    private BigDecimal status;

    private Date createTime;

    private BigDecimal orders;

    private static final long serialVersionUID = 1L;

    public UpmsPermission(BigDecimal id, BigDecimal systemId, BigDecimal pid, String name, BigDecimal type, String permissionValue, String uri, String icon, BigDecimal status, Date createTime, BigDecimal orders) {
        this.id = id;
        this.systemId = systemId;
        this.pid = pid;
        this.name = name;
        this.type = type;
        this.permissionValue = permissionValue;
        this.uri = uri;
        this.icon = icon;
        this.status = status;
        this.createTime = createTime;
        this.orders = orders;
    }

    public UpmsPermission() {
        super();
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getSystemId() {
        return systemId;
    }

    public void setSystemId(BigDecimal systemId) {
        this.systemId = systemId;
    }

    public BigDecimal getPid() {
        return pid;
    }

    public void setPid(BigDecimal pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getType() {
        return type;
    }

    public void setType(BigDecimal type) {
        this.type = type;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
        sb.append(", systemId=").append(systemId);
        sb.append(", pid=").append(pid);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", permissionValue=").append(permissionValue);
        sb.append(", uri=").append(uri);
        sb.append(", icon=").append(icon);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", orders=").append(orders);
        sb.append("]");
        return sb.toString();
    }
}