package cn.ts.core.http.components;

/**
 * wecaht-util-http module excption
 *
 * @author Created by YL on 2017/5/23.
 */
public class HttpException extends Exception {
    private static final long serialVersionUID = -8420932224029402185L;

    private int statusCode = -1;

    public HttpException(String message) {
        super(message);
    }

    public HttpException(Exception cause) {
        super(cause);
    }

    public HttpException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpException(String message, Exception cause) {
        super(message, cause);
    }

    public HttpException(String message, Exception cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;

    }

    public int getStatusCode() {
        return statusCode;
    }
}
