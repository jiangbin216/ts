package cn.ts.dubbo.spring.boot.properties;

/**
 * 在Provider上尽量多配置Consumer端属性
 * <p>
 * <pre>
 * 原因如下：
 *  1、作服务的提供者，比服务使用方更清楚服务性能参数，如调用的超时时间，合理的重试次数，等等
 *  2、在Provider配置后，Consumer不配置则会使用Provider的配置值，即Provider配置可以作为Consumer的缺省值。
 *  否则，Consumer会使用Consumer端的全局设置，这对于Provider不可控的，并且往往是不合理的。
 *  PS: 配置的覆盖规则：1) 方法级配置别优于接口级别，即小Scope优先 2) Consumer端配置 优于 Provider配置 优于 全局配置，最后是Dubbo Hard Code的配置值（见配置文档）
 *  配置的覆盖规则详见： Dubbo配置参考手册
 *
 * Provider上尽量多配置Consumer端的属性，让Provider实现者一开始就思考Provider服务特点、服务质量的问题。
 *
 * @author Create by YL on 2017/06/01.
 */
public class ProviderConstants {
    /**
     * 服务版本
     */
    public static final String version = "1.0.0";
    /**
     * 服务分组
     */
    public static final String group = "default";

    /**
     * 服务延迟暴露（-1：等spring容器初始化完成后再暴露服务）
     * <p>
     * <dubbo:service delay="-1" />
     */
    public static final int delay = -1;
    /**
     * 服务超时
     */
    public static final int timeout = 8000;

}
