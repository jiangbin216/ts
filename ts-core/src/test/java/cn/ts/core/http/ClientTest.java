package cn.ts.core.http;

import cn.ts.core.http.components.Client;
import cn.ts.core.http.components.HttpException;
import cn.ts.core.http.components.Response;
import org.junit.Test;

/**
 * http client 测试
 *
 * @author Created by YL on 2017/7/16.
 */
public class ClientTest {

    @Test
    public void test() throws HttpException {
        Client client = Client.custom().socketTimeout(8000).connectTimeout(8000).connectionRequestTimeout(8000).build();
        Response response = client.get("http://www.baidu.com");
        System.out.println(response.getResponseAsString());
    }
}
