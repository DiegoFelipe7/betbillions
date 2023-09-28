package com.betbillions.walletservice.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan("com.betbillions.walletservice.infrastructure.api")
@ComponentScan(basePackages = "com.betbillions.walletservice.domain.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class ConfigApp {


}
