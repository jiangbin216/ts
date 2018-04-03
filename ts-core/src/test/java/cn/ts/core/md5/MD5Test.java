package cn.ts.core.md5;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Created by YL on 2017/6/10.
 */
public class MD5Test {

    @Test
    public void test() {
        // UTF-8
        String s = MD5.UTF8.md5LowerCase("1234567中国");
        System.out.println(s);
        Assert.assertEquals(s, "97a94f1b3ac108bb90d690dcf283c636");
        s = MD5.UTF8.md5UpperCase("1234567中国");
        System.out.println(s);
        Assert.assertEquals(s, "97A94F1B3AC108BB90D690DCF283C636");

        // GBK
        s = MD5.GBK.md5LowerCase("1234567中国");
        System.out.println(s);
        Assert.assertEquals(s, "5697d858445fc26936f849edffaa7e38");
        s = MD5.GBK.md5UpperCase("1234567中国");
        System.out.println(s);
        Assert.assertEquals(s, "5697D858445FC26936F849EDFFAA7E38");
    }
}
