package cn.ts.web.upms.config;

import cn.ts.web.upms.shiro.filter.UpmsSessionForceLogoutFilter;
import cn.ts.web.upms.shiro.listener.UpmsSessionListener;
import cn.ts.web.upms.shiro.realm.UpmsRealm;
import cn.ts.web.upms.shiro.session.UpmsCachingSessionDAO;
import cn.ts.web.upms.shiro.session.UpmsSessionFactory;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置 shiro 信息
 *
 * @author Created by YL on 2017/6/2.
 */
@Configuration
public class ShiroConfiguration {

    /**
     * realm实现，继承自AuthorizingRealm
     */
    @Bean
    public AuthorizingRealm upmsRealm() {
        return new UpmsRealm();
    }

    // <!-- 重写authc过滤器 -->
    // <bean id="upmsAuthenticationFilter" class="cn.ts.upms.web.shiro.filter.UpmsAuthenticationFilter"/>

    // /**
    //  * 重写 authc 过滤器
    //  */
    // @Bean
    // public AuthenticationFilter upmsAuthenticationFilter() {
    //     return new UpmsAuthenticationFilter();
    // }

    // <!-- 强制退出会话过滤器 -->
    // <bean id="upmsSessionForceLogout" class="cn.ts.upms.web.shiro.filter.UpmsSessionForceLogoutFilter"/>

    /**
     * 强制退出会话过滤器
     */
    @Bean
    public UpmsSessionForceLogoutFilter upmsSessionForceLogoutFilter() {
        return new UpmsSessionForceLogoutFilter();
    }

    /**
     * 会话DAO，可重写，持久化session
     */
    @Bean
    public CachingSessionDAO upmsCachingSessionDAO() {
        return new UpmsCachingSessionDAO();
    }

    /**
     * session工厂
     */
    @Bean
    public SessionFactory upmsSessionFactory() {
        return new UpmsSessionFactory();
    }

    /**
     * 会话监听器
     */
    @Bean
    public SessionListener upmsSessionListener() {
        return new UpmsSessionListener();
    }

    // <!-- 会话管理器 -->
    // <bean id="sessionManager" class="org.apache.shiro.web.session.mgt.DefaultWebSessionManager">
    //     <!-- 全局session超时时间 -->
    //     <property name="globalSessionTimeout" value="${upms.session.timeout}"/>
    //     <!-- sessionDAO -->
    //     <property name="sessionDAO" ref="sessionDAO"/>
    //     <property name="sessionIdCookieEnabled" value="true"/>
    //     <property name="sessionIdCookie" ref="sessionIdCookie"/>
    //     <property name="sessionValidationSchedulerEnabled" value="false"/>
    //     <property name="sessionListeners">
    //         <list>
    //             <ref bean="sessionListener"/>
    //         </list>
    //     </property>
    //     <property name="sessionFactory" ref="sessionFactory"/>
    // </bean>

    /**
     * 会话管理器
     */
    @Bean
    public SessionManager sessionManager(CachingSessionDAO upmsCachingSessionDAO, Cookie sessionIdCookie,
                                         SessionFactory upmsSessionFactory, SessionListener upmsSessionListener) {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        // 会话超时时间：单位ms
        manager.setGlobalSessionTimeout(1800000);
        manager.setSessionDAO(upmsCachingSessionDAO);
        manager.setSessionIdCookieEnabled(true);
        //
        manager.setSessionValidationSchedulerEnabled(false);
        manager.setSessionIdCookie(sessionIdCookie);
        manager.setSessionFactory(upmsSessionFactory);
        List<SessionListener> listeners = new ArrayList<>();
        listeners.add(upmsSessionListener);
        manager.setSessionListeners(listeners);
        // 去掉 JSESSIONID
        manager.setSessionIdUrlRewritingEnabled(false);
        return manager;
    }

    // <!-- rememberMe缓存cookie -->
    // <bean id="rememberMeCookie" class="org.apache.shiro.web.servlet.SimpleCookie">
    //     <constructor-arg value="rememberMe"/>
    //     <!-- 不会暴露给客户端 -->
    //     <property name="httpOnly" value="true"/>
    //     <!-- 记住我cookie生效时间 -->
    //     <property name="maxAge" value="${upms.rememberMe.timeout}"/>
    // </bean>

    /**
     * 记住密码 Cookie。rememberMe 缓存 cookie
     */
    // @Bean("rememberMeCookie")
    // @Qualifier("rememberMeCookie")
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        // simpleCookie.setMaxAge(259200);
        cookie.setMaxAge(259200);
        return cookie;
    }

    // <!-- rememberMe管理器 -->
    // <bean id="rememberMeManager" class="org.apache.shiro.web.mgt.CookieRememberMeManager">
    //     <!-- rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度（128 256 512 位）-->
    //     <property name="cipherKey" value="#{T(org.apache.shiro.codec.Base64).decode('4AvVhmFLUs0KTA3Kprsdag==')}"/>
    //     <property name="cookie" ref="rememberMeCookie"/>
    // </bean>

    /**
     * 记住密码 rememberMe 管理器
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        manager.setCookie(rememberMeCookie);
        return manager;
    }

    // <!-- 会话Cookie模板 -->
    // <bean id="sessionIdCookie" class="org.apache.shiro.web.servlet.SimpleCookie">
    //     <!-- 不会暴露给客户端 -->
    //     <property name="httpOnly" value="true"/>
    //     <!-- 设置Cookie的过期时间，秒为单位，默认-1表示关闭浏览器时过期Cookie -->
    //     <property name="maxAge" value="-1"/>
    //     <!-- Cookie名称 -->
    //     <property name="name" value="${upms.session.id}"/>
    // </bean>

    /**
     * 会话 Cookie 模板
     */
    @Bean("sessionIdCookie")
    @Qualifier("sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie("ts-upms-web-session-id");
        cookie.setHttpOnly(true);
        // -1：关闭浏览器后失效
        cookie.setMaxAge(-1);
        return cookie;
    }

    // <!-- 核心安全事务管理器 -->
    // <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
    //     <property name="realms">
    //         <list>
    //             <ref bean="upmsRealm"/>
    //         </list>
    //     </property>
    //     <property name="sessionManager" ref="sessionManager"/>
    //     <property name="rememberMeManager" ref="rememberMeManager"/>
    // </bean>

    /**
     * 核心安全事务管理器
     */
    @Bean
    public SecurityManager securityManager(AuthorizingRealm upmsRealm, SessionManager sessionManager,
                                           CookieRememberMeManager rememberMeManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // securityManager.setRealms();
        manager.setRealm(upmsRealm);
        manager.setSessionManager(sessionManager);
        manager.setRememberMeManager(rememberMeManager);
        return manager;
    }

    // <!-- Shiro的Web过滤器 -->
    // <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
    //     <property name="securityManager" ref="securityManager"/>
    //     <property name="loginUrl" value="${sso.server.url}"/>
    //     <property name="successUrl" value="${upms.successUrl}"/>
    //     <property name="unauthorizedUrl" value="${upms.unauthorizedUrl}"/>
    //     <property name="filters">
    //         <util:map>
    //             <entry key="authc" value-ref="upmsAuthenticationFilter"/>
    //         </util:map>
    //     </property>
    //     <property name="filterChainDefinitions">
    //         <value>
    //             /manage/** = upmsSessionForceLogout,authc
    //  /manage/index = user
    //  /druid/** = user
    //  /swagger-ui.html = user
    //  /resources/** = anon
    //  /** = anon
    //  </value>
    //  </property>
    //  </bean>

    /**
     * Shiro 的 Web 过滤器链
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager/*, AuthenticationFilter
            upmsAuthenticationFilter*/) {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);
        //配置登录的url和登录成功的url
        filter.setLoginUrl("/sso/login"); // 默认登录访问URL
        filter.setSuccessUrl("/manage/index"); // 登录成功后跳转URL
        filter.setUnauthorizedUrl("/403"); // 没有权限跳转URL

        // Map<String, Filter> filt = new LinkedHashMap<>();
        // filt.put("authc", upmsAuthenticationFilter);
        // filter.setFilters(filt);

        /**
         * 配置shiro拦截器链
         */
        // filterChainDefinitionMap 必须是 LinkedHashMap 因为它必须保证有序
        Map<String, String> chain = new LinkedHashMap<>();
        // anon-表示可以匿名访问， authc-表示需要认证才可以访问
        chain.put("/manage/**", "upmsSessionForceLogoutFilter, authc");
        chain.put("/manage/index", "user");
        chain.put("/druid/**", "user");
        chain.put("/swagger-ui.html", "user");
        chain.put("/resources/**", "anon");
        chain.put("/**", "anon");
        filter.setFilterChainDefinitionMap(chain);
        return filter;
    }

    // /**
    //  * 注册DelegatingFilterProxy（Shiro）
    //  * shiroFilter : DelegatingFilterProxy作用是自动到spring容器查找名字为shiroFilter（filter-name）的bean并把所有Filter
    //  * 的操作委托给它。然后将shiroFilter配置到spring容器即可
    //  */
    // @Bean
    // public FilterRegistrationBean filterRegistrationBean() {
    //     FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
    //     filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
    //     //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
    //     filterRegistration.addInitParameter("targetFilterLifecycle", "true");
    //     // filterRegistration.setEnabled(true);
    //     filterRegistration.setName("shiroFilter");
    //     filterRegistration.addUrlPatterns("/*");
    //     return filterRegistration;
    // }

    // <!-- 设置SecurityUtils，相当于调用SecurityUtils.setSecurityManager(securityManager) -->
    // <bean class="org.springframework.beans.factory.config.MethodInvokingFactoryBean">
    //     <property name="staticMethod" value="org.apache.shiro.SecurityUtils.setSecurityManager"/>
    //     <property name="arguments" ref="securityManager"/>
    // </bean>

    /**
     * 在方法中注入 SecurityManager，进行代理控制
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager securityManager) {
        MethodInvokingFactoryBean factory = new MethodInvokingFactoryBean();
        factory.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factory.setArguments(new Object[]{securityManager});
        return factory;
    }

    // <!-- Shiro生命周期处理器 -->
    // <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/>

    /**
     * Shiro 生命周期处理器。保证实现了shiro内部的lifecycle函数的bean执行
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    // <!-- 开启Shiro Spring AOP权限注解@RequiresPermissions的支持 -->
    // <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
    // depends-on="lifecycleBeanPostProcessor"/>

    /**
     * 启用shiro授权注解拦截方式，AOP式方法级权限检测
     */
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    // <bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
    //     <property name="securityManager" ref="securityManager"/>
    // </bean>
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
