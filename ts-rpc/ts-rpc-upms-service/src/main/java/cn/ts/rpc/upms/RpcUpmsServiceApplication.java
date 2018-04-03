package cn.ts.rpc.upms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动器
 *
 * @author Created by YL on 2017/6/1.
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.ts.rpc.upms.mapper"})
public class RpcUpmsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcUpmsServiceApplication.class, args);
        synchronized (RpcUpmsServiceApplication.class) {
            while (true) {
                try {
                    RpcUpmsServiceApplication.class.wait();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
