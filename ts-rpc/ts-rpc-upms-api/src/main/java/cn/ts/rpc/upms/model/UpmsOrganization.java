package cn.ts.rpc.upms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *   upms系统-组织表（created by yl on 2017-07-18）
 * 
 * <pre>
 * <table>
 *  <tr><td>属性名</td><td>数据类型</td><td>表字段名</td><td>表字段描述</td><td>默认值</td></tr>
 *  <tr><td>pid</td><td>BigDecimal</td><td>PID</td><td>所属上级</td><td></td></tr>
 *  <tr><td>name</td><td>String</td><td>NAME</td><td>组织名称</td><td></td></tr>
 *  <tr><td>description</td><td>String</td><td>DESCRIPTION</td><td>组织描述</td><td></td></tr>
 *  <tr><td>createTime</td><td>Date</td><td>CREATE_TIME</td><td>创建时间</td><td>sysdate
</td></tr>
 * </table>
 * </pre>
 * 
 * @author Created by Mybatis Generator on 2017/07/21.
 */
@Table(name = "UPMS_ORGANIZATION")
public class UpmsOrganization implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    private BigDecimal pid;

    private String name;

    private String description;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public UpmsOrganization(BigDecimal id, BigDecimal pid, String name, String description, Date createTime) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.description = description;
        this.createTime = createTime;
    }

    public UpmsOrganization() {
        super();
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}