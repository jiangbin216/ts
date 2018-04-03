package cn.ts.web.upms.config;

import cn.ts.web.upms.interceptor.UpmsInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置自定义拦截器
 * <pre>
 *  1、@EnableWebMvc+extends WebMvcConfigurationAdapter，在扩展的类中重写父类的方法即可，这种方式会屏蔽springboot的@EnableAutoConfiguration中的设置
 *  2、extends WebMvcConfigurationSupport，在扩展的类中重写父类的方法即可，这种方式会屏蔽springboot的@EnableAutoConfiguration中的设置
 *  3、extends WebMvcConfigurationAdapter，在扩展的类中重写父类的方法即可，这种方式依旧使用springboot的@EnableAutoConfiguration中的设置
 * </pre>
 *
 * @author Created by YL on 2017/6/5.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    // /**
    //  * 配置servlet处理
    //  */
    // @Override
    // public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    //     configurer.enable();
    // }
    //
    // @Bean
    // public InternalResourceViewResolver viewResolver() {
    //     InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    //     resolver.setPrefix("/WEB-INF/jsp/");
    //     resolver.setSuffix("");
    //     return resolver;
    // }
    //

    // /**
    //  * 添加静态资源路径
    //  *
    //  * @param registry
    //  */
    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     registry.addResourceHandler("/webjars").addResourceLocations("classpath:/META-INF/resources/webjars/");
    //     registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    // }

    @Bean
    public UpmsInterceptor upmsInterceptor() {
        return new UpmsInterceptor();
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(upmsInterceptor())
                .addPathPatterns("/manage/**");
    }
}
