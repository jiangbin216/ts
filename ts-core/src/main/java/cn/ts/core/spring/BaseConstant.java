package cn.ts.core.spring;

/**
 * 常量
 *
 * @author Created by YL on 2017/6/14.
 */
public enum BaseConstant {
    SUCCESS(0, "success"),
    FAILED(-1, "failed");

    public int errcode;
    public String errmsg;

    BaseConstant(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
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
}
