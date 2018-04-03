package cn.ts.core.md5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 签名工具类
 *
 * @author Created by YL on 2017/5/23.
 */
public enum MD5 {
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
     * md5签名
     *
     * @param str 原始字符
     * @return 小写md5签名
     */
    public String md5LowerCase(String str) {
        return md5(str, this.getCharset());
    }

    /**
     * md5签名
     *
     * @param str 原始字符
     * @return 大写md5签名
     */
    public String md5UpperCase(String str) {
        return md5(str, this.getCharset()).toUpperCase();
    }

    /**
     * md5签名
     *
     * @param str     原始字符
     * @param charset 编码格式
     * @return 小写md5签名
     */
    private static String md5(String str, String charset) {
        if (str == null) {
            return "";
        }
        if (charset == null) {
            return "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes(charset));
            byte[] bytes = md.digest();
            StringBuilder buffer = new StringBuilder(32);
            for (int i = 0; i < bytes.length; i++) {
                int num = bytes[i] & 0xFF;
                String b = num < 16 ? "0" + Integer.toHexString(num) : Integer.toHexString(num);
                buffer.append(b);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("签名算法不存在", e);
        } catch (UnsupportedEncodingException e) {
            // 如果可以明确编码，这个异常可以忽略
            throw new RuntimeException("编码格式不存在", e);
        }
    }
}
