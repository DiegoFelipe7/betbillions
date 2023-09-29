package com.betbillions.walletservice.infrastructure.driver.config;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper reactiveCommonsObjectMapper() {
        return new ObjectMapper() {
            @Override
            public <T> T map(Object src, Class<T> target) {
                return null;
            }

            @Override
            public <T> T mapBuilder(Object src, Class<T> target) {
                return null;
            }
        };
    }

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }
    @Bean
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }
}
