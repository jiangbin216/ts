package cn.ts.dubbo.spring.boot.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * 主动停止线程的监听方法
 *
 * @author Created by YL on 2017/6/5.
 */
public class DubboHolderListener implements ApplicationListener {
    private static Thread holdThread;
    private static Boolean running = Boolean.FALSE;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationPreparedEvent) {
            if (running == Boolean.FALSE)
                running = Boolean.TRUE;
            if (holdThread == null) {
                holdThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (running && !Thread.currentThread().isInterrupted()) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                }, "Dubbo-Holder");
                holdThread.setDaemon(false);
                holdThread.start();
            }
        }
        if (event instanceof ContextClosedEvent) {
            running = Boolean.FALSE;
            if (null != holdThread) {
                holdThread.interrupt();
                holdThread = null;
            }
        }
    }

    public static void stopApplicationContext(Boolean stop) {
        running = stop.booleanValue();
        if (null != holdThread) {
            holdThread.interrupt();
            holdThread = null;
        }
    }
}
