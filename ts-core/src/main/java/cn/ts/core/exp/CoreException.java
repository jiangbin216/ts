package cn.ts.core.exp;

/**
 * 统一异常封装
 *
 * @author Created by YL on 2017/7/4.
 */
public class CoreException extends Exception {
    private static final long serialVersionUID = -8420932224029402185L;

    public CoreException(String msg) {
        super(msg);
    }

    public CoreException(Exception cause) {
        super(cause);
    }

    public CoreException(String msg, Exception cause) {
        super(msg, cause);
    }
}
