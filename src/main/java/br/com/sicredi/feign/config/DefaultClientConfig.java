package br.com.sicredi.feign.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class DefaultClientConfig {
    @Bean
    private RequestInterceptor interceptor() {
        return template -> {
            template.header("Accpet", "application/json");
            template.header("Content-Type", "application/json");
        };
    }
}
