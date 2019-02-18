package com.baoying.dis;

import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class DisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisApplication.class, args);
    }

    /**
     * 开发模式中 直接装饰原数据源以输出执行 SQL
     *
     * @return
     */
    @Bean
    @Profile("dev")
    public BeanPostProcessor logDataSourceBeanProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof DataSource) {
                    return ProxyDataSourceBuilder.create(((DataSource) bean)).logQueryToSysOut().multiline().build();
                }
                return bean;
            }
        };
    }

    /**
     * 打印容器管理的 Bean 方便随时检查相关情况
     *
     * @param event
     */
    @EventListener(classes = ContextRefreshedEvent.class, condition = "#event.applicationContext.environment.acceptsProfiles('logbeans')")
    public void logSpringBeanDefinitions(ContextRefreshedEvent event) {
        final AtomicInteger index = new AtomicInteger(0);
        Stream.of(event.getApplicationContext().getBeanDefinitionNames()).sorted((o1, o2) -> {
            boolean b1 = o1.indexOf('.') > 0;
            boolean b2 = o2.indexOf('.') > 0;
            if (b1 == b2) {
                return o1.compareTo(o2);
            }
            return b1 && !b2 ? 1 : -1;
        }).forEach(name -> {
            int n = index.incrementAndGet();
            System.out.println(String.format("[%03d] Name:%s, Type:%s", index.incrementAndGet(), name, event.getApplicationContext().getType(name)));
        });
    }
}

