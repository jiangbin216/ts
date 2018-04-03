package cn.ts.dubbo.spring.boot;

import cn.ts.dubbo.spring.boot.properties.DubboProperties;
import cn.ts.dubbo.spring.boot.properties.ProviderConstants;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dubbo common configuration
 *
 * @author Created by YL on 2017/6/5.
 */
@Configuration
@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfiguration {
    @Autowired
    private DubboProperties dubboProperties;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig config = dubboProperties.getApplication();
        if (config == null) {
            config = new ApplicationConfig();
        } else {
            String name = config.getName();
            if (StringUtils.isBlank(name))
                throw new NullPointerException("dubbo.application.name canot by null");
        }
        return config;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig config = dubboProperties.getRegistry();
        if (config == null) {
            config = new RegistryConfig();
        } else {
            String address = config.getAddress();
            if (StringUtils.isBlank(address))
                throw new NullPointerException("dubbo.registry.address canot by null");
        }
        return config;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig config = dubboProperties.getProtocol();
        if (config == null) {
            config = new ProtocolConfig();
        }
        return config;
    }

    // @Bean
    // public MonitorConfig monitorConfig() {
    //     MonitorConfig config = dubboProperties.getMonitor();
    //     if (config == null) {
    //         config = new MonitorConfig();
    //     }
    //     return config;
    // }

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig config = dubboProperties.getProvider();
        if (config == null) {
            config = new ProviderConfig();
        }
        if (StringUtils.isBlank(config.getVersion()))
            config.setVersion(ProviderConstants.version);
        if (StringUtils.isBlank(config.getGroup()))
            config.setGroup(ProviderConstants.group);
        if (config.getDelay() == null)
            config.setDelay(ProviderConstants.delay);
        if (config.getTimeout() == null)
            config.setTimeout(ProviderConstants.timeout);
        return config;
    }

    // @Bean
    // public ModuleConfig moduleConfig() {
    //     ModuleConfig config = dubboProperties.getModule();
    //     if (config == null) {
    //         config = new ModuleConfig();
    //     }
    //     return config;
    // }

    // @Bean
    // public MethodConfig methodConfig() {
    //     MethodConfig config = dubboProperties.getMethod();
    //     if (config == null) {
    //         config = new MethodConfig();
    //     }
    //     return config;
    // }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig config = dubboProperties.getConsumer();
        if (config == null) {
            config = new ConsumerConfig();
        }
        if (StringUtils.isBlank(config.getVersion()))
            config.setVersion(ProviderConstants.version);
        if (StringUtils.isBlank(config.getGroup()))
            config.setGroup(ProviderConstants.group);
        if (config.getTimeout() == null)
            config.setTimeout(ProviderConstants.timeout);
        return config;
    }
}
