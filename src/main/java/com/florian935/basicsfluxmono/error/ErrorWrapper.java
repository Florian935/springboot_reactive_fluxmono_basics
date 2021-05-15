package com.florian935.basicsfluxmono.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author florian935
 */
@Configuration
public class ErrorWrapper {

    @Bean
    public OnErrorMap onErrorMap() {
        return new OnErrorMap();
    }

    @Bean
    public OnErrorResume onErrorResume() {
        return new OnErrorResume();
    }

    @Bean
    public OnErrorReturn onErrorReturn() {
        return new OnErrorReturn();
    }
}
