package com.betbillion.authservice.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan("com.betbillion.authservice.infrastructure.points.api")
@ComponentScan(basePackages = "com.betbillion.authservice.domain.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class ConfigApp {


}
