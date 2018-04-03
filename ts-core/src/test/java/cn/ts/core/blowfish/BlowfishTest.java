package cn.ts.core.blowfish;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Created by YL on 2017/5/23.
 */
public class BlowfishTest {
    @Test
    public void test() throws Exception {
        String str = "1453081255536|1|4401|1525767486|83280065|三维空间";
        String ts = "1453081255536";
        System.out.println("原始的报文：" + str);
        System.out.println("密钥：" + ts);
        // UTF-8
        System.out.println("UTF-8");
        String encrypt = Blowfish.UTF8.encrypt(str, ts);
        System.out.println("加密后报文：" + encrypt);
        String decrypt = Blowfish.UTF8.decrypt(encrypt, ts);
        System.out.println("解密后报文：" + decrypt);
        Assert.assertEquals(str, decrypt);

        // GBK
        System.out.println("GBK");
        encrypt = Blowfish.GBK.encrypt(str, ts);
        System.out.println("加密后报文：" + encrypt);
        decrypt = Blowfish.GBK.decrypt(encrypt, ts);
        System.out.println("解密后报文：" + decrypt);
        Assert.assertEquals(str, decrypt);
    }
}
