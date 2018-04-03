package cn.ts.core.aes;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Created by YL on 2017/6/8.
 */
public class AESTest {

    @Test
    public void test() throws Exception {
        String content = "1213213|sdfsdfs|三维空间";
        // UTF-8
        System.out.println("UTF-8");
        String encryptString = AES.UTF8.encode(content);
        System.out.println("加密后：" + encryptString);
        String decryptString = AES.UTF8.decode(encryptString);
        System.out.println("解密后：" + decryptString);
        Assert.assertEquals(content, decryptString);
        // GBK
        System.out.println("GBK");
        encryptString = AES.GBK.encode(content);
        System.out.println("加密后：" + encryptString);
        decryptString = AES.GBK.decode(encryptString);
        System.out.println("解密后：" + decryptString);
        Assert.assertEquals(content, decryptString);
    }
}
