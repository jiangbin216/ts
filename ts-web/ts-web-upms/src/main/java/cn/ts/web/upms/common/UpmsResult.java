package cn.ts.web.upms.common;

/**
 * upms系统常量枚举类
 *
 * @author Created by shuzheng on 2017/2/18.
 */
public class UpmsResult {
    // 状态码：1成功，其他为失败
    public int code;

    // 成功为success，其他为失败原因
    public String message;

    // 数据结果集
    public Object data;

    public UpmsResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public UpmsResult(UpmsResultConstant upmsResultConstant, Object data) {
        this(upmsResultConstant.getCode(), upmsResultConstant.getMessage(), data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
