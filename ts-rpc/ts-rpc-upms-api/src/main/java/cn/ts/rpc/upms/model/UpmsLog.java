package cn.ts.rpc.upms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *   upms系统-用户操作日志（created by yl on 2017-07-18）
 * 
 * <pre>
 * <table>
 *  <tr><td>属性名</td><td>数据类型</td><td>表字段名</td><td>表字段描述</td><td>默认值</td></tr>
 *  <tr><td>description</td><td>String</td><td>DESCRIPTION</td><td>操作描述</td><td></td></tr>
 *  <tr><td>username</td><td>String</td><td>USERNAME</td><td>操作用户</td><td></td></tr>
 *  <tr><td>startTime</td><td>Date</td><td>START_TIME</td><td>操作时间</td><td>sysdate
</td></tr>
 *  <tr><td>spendTime</td><td>BigDecimal</td><td>SPEND_TIME</td><td>消耗时间</td><td></td></tr>
 *  <tr><td>basePath</td><td>String</td><td>BASE_PATH</td><td>根路径</td><td></td></tr>
 *  <tr><td>uri</td><td>String</td><td>URI</td><td>URI</td><td></td></tr>
 *  <tr><td>url</td><td>String</td><td>URL</td><td>URL</td><td></td></tr>
 *  <tr><td>method</td><td>String</td><td>METHOD</td><td>请求类型</td><td></td></tr>
 *  <tr><td>userAgent</td><td>String</td><td>USER_AGENT</td><td>用户标识</td><td></td></tr>
 *  <tr><td>ip</td><td>String</td><td>IP</td><td>IP地址</td><td></td></tr>
 *  <tr><td>permissions</td><td>String</td><td>PERMISSIONS</td><td>权限值</td><td></td></tr>
 *  <tr><td>parameter</td><td>String</td><td>PARAMETER</td><td>请求参数值</td><td></td></tr>
 *  <tr><td>result</td><td>String</td><td>RESULT</td><td>请求返回结果值</td><td></td></tr>
 * </table>
 * </pre>
 * 
 * @author Created by Mybatis Generator on 2017/07/21.
 */
@Table(name = "UPMS_LOG")
public class UpmsLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    private String description;

    private String username;

    private Date startTime;

    private BigDecimal spendTime;

    private String basePath;

    private String uri;

    private String url;

    private String method;

    private String userAgent;

    private String ip;

    private String permissions;

    private String parameter;

    private String result;

    private static final long serialVersionUID = 1L;

    public UpmsLog(BigDecimal id, String description, String username, Date startTime, BigDecimal spendTime, String basePath, String uri, String url, String method, String userAgent, String ip, String permissions, String parameter, String result) {
        this.id = id;
        this.description = description;
        this.username = username;
        this.startTime = startTime;
        this.spendTime = spendTime;
        this.basePath = basePath;
        this.uri = uri;
        this.url = url;
        this.method = method;
        this.userAgent = userAgent;
        this.ip = ip;
        this.permissions = permissions;
        this.parameter = parameter;
        this.result = result;
    }

    public UpmsLog() {
        super();
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(BigDecimal spendTime) {
        this.spendTime = spendTime;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("id=").append(id);
        sb.append(", description=").append(description);
        sb.append(", username=").append(username);
        sb.append(", startTime=").append(startTime);
        sb.append(", spendTime=").append(spendTime);
        sb.append(", basePath=").append(basePath);
        sb.append(", uri=").append(uri);
        sb.append(", url=").append(url);
        sb.append(", method=").append(method);
        sb.append(", userAgent=").append(userAgent);
        sb.append(", ip=").append(ip);
        sb.append(", permissions=").append(permissions);
        sb.append(", parameter=").append(parameter);
        sb.append(", result=").append(result);
        sb.append("]");
        return sb.toString();
    }
}