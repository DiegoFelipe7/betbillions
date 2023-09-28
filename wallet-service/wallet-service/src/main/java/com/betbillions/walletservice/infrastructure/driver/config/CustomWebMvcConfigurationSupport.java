package com.betbillions.walletservice.infrastructure.driver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;

@Configuration
public class CustomWebMvcConfigurationSupport /*extends WebMvcConfigurationSupport*/ {
/*
    @Bean
    public PageRequest defaultPageRequest() {
        return PageRequest.of(0, 100);
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        SortHandlerMethodArgumentResolver argumentResolver = new SortHandlerMethodArgumentResolver();
        argumentResolver.setSortParameter("sort");
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver(argumentResolver);
        resolver.setFallbackPageable(defaultPageRequest());
        resolver.setPageParameterName("page");
        resolver.setSizeParameterName("size");
        argumentResolvers.add(resolver);
    }*/
}
