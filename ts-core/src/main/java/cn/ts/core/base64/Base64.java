package cn.ts.core.base64;

import java.io.UnsupportedEncodingException;

/**
 * Base64 工具类
 *
 * @author Created by YL on 2017/5/23.
 */
public enum Base64 {
    GBK {
        public String getCharset() {
            return "GBK";
        }
    },

    UTF8 {
        public String getCharset() {
            return "UTF-8";
        }
    };

    /**
     * 获取编码
     */
    public String getCharset() {
        throw new AbstractMethodError();
    }

    /**
     * base64编码
     */
    public String encode(String str) {
        try {
            return encode(str.getBytes(this.getCharset()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持的编码格式", e);
        }
    }

    /**
     * base64编码
     */
    public String encode(byte[] str) {
        try {
            byte[] b = org.apache.commons.codec.binary.Base64.encodeBase64(str);
            return new String(b, this.getCharset());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持的编码格式", e);
        }
    }

    /**
     * base64解码
     */
    public String decode(String str) {
        try {
            byte[] b = decode2byte(str);
            return new String(b, this.getCharset());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持的编码格式", e);
        }
    }

    /**
     * base64解码
     */
    public byte[] decode2byte(String str) {
        try {
            return org.apache.commons.codec.binary.Base64.decodeBase64(str.getBytes(this.getCharset()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持的编码格式", e);
        }
    }
}
