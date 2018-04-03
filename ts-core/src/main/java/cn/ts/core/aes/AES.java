package cn.ts.core.aes;

import cn.ts.core.exp.CoreException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * AES加解密工具类
 *
 * @author Created by YL on 2017/6/8.
 */
public enum AES {
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
    private static final String DEFAULT_KEY = "ts-util-aes";

    private static ConcurrentMap<String, SecretKey> KEY_MAP = new ConcurrentHashMap<>();

    /**
     * 获取编码
     */
    public String getCharset() {
        throw new AbstractMethodError();
    }

    /**
     * 把构建好的 SecretKey 保存到内存中，以方便下次使用时，不用再重新构建
     *
     * @param key 密钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private SecretKey generatorKey(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        SecretKey secretKey = KEY_MAP.get(key);
        if (secretKey == null) {
            // 1、构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            // 2、根据传入的字节数组，生成一个128位的随机源
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes(this.getCharset()));
            keygen.init(128, random);
            // 3、产生原始对称密钥
            SecretKey originalKey = keygen.generateKey();
            // 4、获得原始对称密钥的字节数组
            byte[] raw = originalKey.getEncoded();
            // 5、根据字节数组生成AES密钥
            secretKey = new SecretKeySpec(raw, "AES");
            KEY_MAP.put(key, secretKey);
        }
        return secretKey;
    }

    /**
     * AES 加密，使用默认密钥
     *
     * @param content 原始报文：要加密的内容
     * @return 密文
     */
    public String encode(String content) throws CoreException {
        return encode(DEFAULT_KEY, content);
    }

    /**
     * AES 加密
     *
     * @param key     密钥
     * @param content 原始报文：要加密的内容
     * @return 密文
     */
    public String encode(String key, String content) throws CoreException {
        try {
            // 6、根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // 7、初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, generatorKey(key));
            // 8、获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byteEncode = content.getBytes(this.getCharset());
            // 9、根据密码器的初始化方式--加密：将数据加密
            byte[] byteAES = cipher.doFinal(byteEncode);
            // 10、将加密后的数据转换为字符串
            return new String(Base64.encodeBase64(byteAES), this.getCharset());
            // return new String(new BASE64Encoder().encode(byteAES));
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
     * AES 解密，使用默认密钥
     *
     * @param content 密文
     * @return 原始报文
     */
    public String decode(String content) throws CoreException {
        return decode(DEFAULT_KEY, content);
    }

    /**
     * AES 解密
     *
     * @param key     密钥
     * @param content 密文
     * @return 原始报文
     */
    public String decode(String key, String content) throws CoreException {
        try {
            // 6、根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // 7、初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, generatorKey(key));
            // 8、将加密并编码后的内容解码成字节数组
            byte[] byteContent = Base64.decodeBase64(content);
            // 9、解密
            byte[] byteDecode = cipher.doFinal(byteContent);
            // 10、将加密后的数据转换为字符串
            return new String(byteDecode, this.getCharset());
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
}
