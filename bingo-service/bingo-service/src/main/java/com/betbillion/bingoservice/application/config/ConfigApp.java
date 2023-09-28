package com.betbillion.bingoservice.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan("com.betbillion.bingoservice.infrastructure.api")
@ComponentScan(basePackages = "com.betbillion.bingoservice.domain.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class ConfigApp {


}
