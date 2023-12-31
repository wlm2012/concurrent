package com.study.concurrent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync(proxyTargetClass = true)
@EnableScheduling
@Configuration
public class TaskExecutorConfig {

/*    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    public AsyncTaskExecutor applicationTaskExecutor() {
        return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
    }

    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
        return protocolHandler -> {
            protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        };
    }*/
}