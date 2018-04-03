package cn.ts.web.upms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * web container 启动器
 *
 * @author Created by YL on 2017/6/1.
 */
@SpringBootApplication
// @EnableDubboConfiguration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
// @ComponentScan("cn.ts.upms.web")
// public class UpmsWebApplication {
public class UpmsWebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UpmsWebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(UpmsWebApplication.class, args);
    }
}
