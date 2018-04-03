package cn.ts.core.base64;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author Created by YL on 2017/5/23.
 */
public class Base64Test {
    @Test
    public void test() throws UnsupportedEncodingException {
        String str = "sjdlfsjdlfj|三等奖路口附近岁的老妇|3213213是，。，。，、";
        System.out.println("原来的：" + str);
        // UTF-8
        System.out.println("UTF-8");
        String encodeBase64 = Base64.UTF8.encode(str);
        System.out.println("编码后：" + encodeBase64);
        String decodeBase64 = Base64.UTF8.decode(encodeBase64);
        System.out.println("解码后：" + decodeBase64);
        Assert.assertEquals(str, decodeBase64);
        // GBK
        System.out.println("GBK");
        encodeBase64 = Base64.GBK.encode(str);
        System.out.println("编码后：" + encodeBase64);
        decodeBase64 = Base64.GBK.decode(encodeBase64);
        System.out.println("解码后：" + decodeBase64);
        Assert.assertEquals(str, decodeBase64);
    }
}
