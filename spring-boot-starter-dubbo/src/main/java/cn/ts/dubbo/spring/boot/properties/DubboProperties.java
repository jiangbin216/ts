package cn.ts.dubbo.spring.boot.properties;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ModuleConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.validation.constraints.NotNull;

/**
 * dubbo相关配置
 *
 * @author Created by YL on 2017/6/6.
 */
@ConfigurationProperties(prefix = "dubbo")
public class DubboProperties {
    @NotNull
    private String scan;
    @NestedConfigurationProperty
    private ApplicationConfig application;
    @NestedConfigurationProperty
    private RegistryConfig registry;
    @NestedConfigurationProperty
    private ProtocolConfig protocol;
    @NestedConfigurationProperty
    private MonitorConfig monitor;
    @NestedConfigurationProperty
    private ProviderConfig provider;
    @NestedConfigurationProperty
    private ModuleConfig module;
    @NestedConfigurationProperty
    private MethodConfig method;
    @NestedConfigurationProperty
    private ConsumerConfig consumer;

    public String getScan() {
        return scan;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

    public ApplicationConfig getApplication() {
        return application;
    }

    public void setApplication(ApplicationConfig application) {
        this.application = application;
    }

    public RegistryConfig getRegistry() {
        return registry;
    }

    public void setRegistry(RegistryConfig registry) {
        this.registry = registry;
    }

    public ProtocolConfig getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolConfig protocol) {
        this.protocol = protocol;
    }

    public MonitorConfig getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorConfig monitor) {
        this.monitor = monitor;
    }

    public ProviderConfig getProvider() {
        return provider;
    }

    public void setProvider(ProviderConfig provider) {
        this.provider = provider;
    }

    public ModuleConfig getModule() {
        return module;
    }

    public void setModule(ModuleConfig module) {
        this.module = module;
    }

    public MethodConfig getMethod() {
        return method;
    }

    public void setMethod(MethodConfig method) {
        this.method = method;
    }

    public ConsumerConfig getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerConfig consumer) {
        this.consumer = consumer;
    }
}
