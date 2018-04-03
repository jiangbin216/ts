package cn.ts.core.spring;

/**
 * 返回json对象实体类
 * <pre>
 *     返回格式：{"data":{},"errcode":0,"errmsg":"sdfsdf"}
 * </pre>
 *
 * @author Created by YL on 2017/6/14.
 */
public class BaseResult {
    /**
     * 状态码：0-成功，其他为失败
     */
    public int errcode;

    /**
     * 成功返回：success，失败返回具体原因
     */
    public String errmsg;

    /**
     * 返回的数据结果集
     */
    public Object data;

    public BaseResult(int errcode, String errmsg, Object data) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = data;
    }

    public BaseResult(BaseConstant constant, Object data) {
        this(constant.getErrcode(), constant.getErrmsg(), data);
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseResult{");
        sb.append("errcode=").append(errcode);
        sb.append(", errmsg='").append(errmsg).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
