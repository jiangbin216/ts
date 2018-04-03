package cn.ts.core.blowfish;

import cn.ts.core.exp.CoreException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Blowfish 加解密工具类
 *
 * @author Created by YL on 2017/5/23.
 */
public enum Blowfish {
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
     * 加密字符串
     *
     * @param str 明文
     * @param key 13位数字的时间戳
     * @return 加密后的密文
     */
    public String encrypt(String str, String key) throws CoreException {
        try {
            str = string2hex(str, this.getCharset());
            byte[] bt = string2byte(str);
            byte[] value = encrypt(key, bt, this.getCharset());
            return byte2string(value);
        } catch (UnsupportedEncodingException e) {
            throw new CoreException("不支持字符编码。", e);
        } catch (NoSuchAlgorithmException e) {
            throw new CoreException("在当前环境中找不到该加密算法。", e);
        } catch (IllegalBlockSizeException e) {
            throw new CoreException("分组密码的数据的长度不正确。", e);
        } catch (BadPaddingException e) {
            throw new CoreException("填充机制未正确填充数据时。", e);
        } catch (InvalidKeyException e) {
            throw new CoreException("无效键（无效编码，长度错误，未初始化等）。", e);
        } catch (NoSuchPaddingException e) {
            throw new CoreException("该环境没有此填充机制。", e);
        }
    }

    /**
     * 解密字符串
     *
     * @param str 密文
     * @param key 13位数字的时间戳
     * @return 解密后的明文
     */
    public String decrypt(String str, String key) throws CoreException {
        try {
            byte[] bt1 = string2byte(str);
            byte[] value = decrypt(key, bt1, this.getCharset());
            return hex2string(byte2string(value), this.getCharset());
        } catch (UnsupportedEncodingException e) {
            throw new CoreException("不支持字符编码。", e);
        } catch (NoSuchAlgorithmException e) {
            throw new CoreException("在当前环境中找不到该加密算法。", e);
        } catch (IllegalBlockSizeException e) {
            throw new CoreException("分组密码的数据的长度不正确。", e);
        } catch (BadPaddingException e) {
            throw new CoreException("填充机制未正确填充数据时。", e);
        } catch (InvalidKeyException e) {
            throw new CoreException("无效键（无效编码，长度错误，未初始化等）。", e);
        } catch (NoSuchPaddingException e) {
            throw new CoreException("该环境没有此填充机制。", e);
        }
    }

    /**
     * 加密：通过密钥key加密byte数组
     *
     * @param key   密钥
     * @param bytes 未加密的字符串
     * @return 加密后的byte数组
     */
    private byte[] encrypt(String key, byte[] bytes, String charset)
            throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException {
        SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(charset), "Blowfish");
        // BlowFish的加密mode是ECB、Padding方式是PKCS5Padding
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, sksSpec);
        byte[] encrypted = cipher.doFinal(bytes);
        return encrypted;
    }

    /**
     * 解密：通过密钥key解密byte数组
     *
     * @param key   密钥
     * @param bytes 加密的byte数组
     * @return 解密后的byte数组
     */
    private byte[] decrypt(String key, byte[] bytes, String charset)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(charset), "Blowfish");
        // BlowFish的加密mode是ECB、Padding方式是PKCS5Padding
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, sksSpec);
        byte[] decrypted = cipher.doFinal(bytes);
        return decrypted;
    }

    /**
     * 解密：16进制字符转字符串
     *
     * @param str     字符串
     * @param charset 编码格式
     */
    private String hex2string(String str, String charset) throws UnsupportedEncodingException {
        String value = "";
        if (str != null && !"".equals(str)) {
            value = new String(string2byte(str), charset);
        }
        return value;
    }

    /**
     * 加密：字符串转16进制字符
     *
     * @param str     字符串
     * @param charset 编码格式
     */
    private String string2hex(String str, String charset) throws UnsupportedEncodingException {
        String value = "";
        if (str != null && !"".equals(str)) {
            value = byte2string(str.getBytes(charset));
        }
        return value;
    }

    /**
     * 把byte数组变换为16進数的字符串。
     *
     * @param bytes byte数组
     */
    private String byte2string(byte[] bytes) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int d = bytes[i];
            if (d < 0) {
                d += 256;
            }
            if (d < 16) {
                buf.append("0");
            }
            buf.append(Integer.toString(d, 16));
        }
        return buf.toString();
    }

    /**
     * 把16進数的字符串变换为byte数组。
     *
     * @param str 16進数的字符串
     */
    private byte[] string2byte(String str) {
        byte[] bytes = new byte[str.length() / 2];
        String b;
        for (int i = 0; i < str.length() / 2; i++) {
            b = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(b, 16);
        }
        return bytes;
    }
}
