package cn.ts.dubbo.spring.boot.initializer;

import com.alibaba.dubbo.config.spring.AnnotationBean;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * dubbo 启动包扫描
 *
 * @author Created by YL on 2017/6/5.
 */
public class DubboConfigurationInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Environment env = applicationContext.getEnvironment();
        String scan = env.getProperty("dubbo.scan");
        if (scan != null) {
            AnnotationBean scanner = BeanUtils.instantiate(AnnotationBean.class);
            scanner.setPackage(scan);
            scanner.setApplicationContext(applicationContext);
            applicationContext.addBeanFactoryPostProcessor(scanner);
            applicationContext.getBeanFactory().addBeanPostProcessor(scanner);
            applicationContext.getBeanFactory().registerSingleton("annotationBean", scanner);
        }
    }
}
