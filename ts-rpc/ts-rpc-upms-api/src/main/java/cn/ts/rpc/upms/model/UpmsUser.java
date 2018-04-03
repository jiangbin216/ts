package cn.ts.rpc.upms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *   upms系统-用户表（created by yl on 2017-07-18）
 * 
 * <pre>
 * <table>
 *  <tr><td>属性名</td><td>数据类型</td><td>表字段名</td><td>表字段描述</td><td>默认值</td></tr>
 *  <tr><td>username</td><td>String</td><td>USERNAME</td><td>帐号</td><td></td></tr>
 *  <tr><td>password</td><td>String</td><td>PASSWORD</td><td>密码MD5(密码+盐)</td><td></td></tr>
 *  <tr><td>salt</td><td>String</td><td>SALT</td><td>盐</td><td></td></tr>
 *  <tr><td>realname</td><td>String</td><td>REALNAME</td><td>真实姓名</td><td></td></tr>
 *  <tr><td>avatar</td><td>String</td><td>AVATAR</td><td>头像</td><td></td></tr>
 *  <tr><td>phone</td><td>String</td><td>PHONE</td><td>电话</td><td></td></tr>
 *  <tr><td>email</td><td>String</td><td>EMAIL</td><td>邮箱</td><td></td></tr>
 *  <tr><td>sex</td><td>BigDecimal</td><td>SEX</td><td>性别</td><td></td></tr>
 *  <tr><td>locked</td><td>BigDecimal</td><td>LOCKED</td><td>状态(0:正常,1:锁定)</td><td>0
</td></tr>
 *  <tr><td>createTime</td><td>Date</td><td>CREATE_TIME</td><td>创建时间</td><td>sysdate
</td></tr>
 * </table>
 * </pre>
 * 
 * @author Created by Mybatis Generator on 2017/07/21.
 */
@Table(name = "UPMS_USER")
public class UpmsUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    private String username;

    private String password;

    private String salt;

    private String realname;

    private String avatar;

    private String phone;

    private String email;

    private BigDecimal sex;

    private BigDecimal locked;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public UpmsUser(BigDecimal id, String username, String password, String salt, String realname, String avatar, String phone, String email, BigDecimal sex, BigDecimal locked, Date createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.realname = realname;
        this.avatar = avatar;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.locked = locked;
        this.createTime = createTime;
    }

    public UpmsUser() {
        super();
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSex() {
        return sex;
    }

    public void setSex(BigDecimal sex) {
        this.sex = sex;
    }

    public BigDecimal getLocked() {
        return locked;
    }

    public void setLocked(BigDecimal locked) {
        this.locked = locked;
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
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", realname=").append(realname);
        sb.append(", avatar=").append(avatar);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", sex=").append(sex);
        sb.append(", locked=").append(locked);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}