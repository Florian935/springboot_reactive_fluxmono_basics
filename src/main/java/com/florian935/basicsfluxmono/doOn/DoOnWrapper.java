package com.florian935.basicsfluxmono.doOn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author florian935
 */
@Configuration
public class DoOnWrapper {

    @Bean
    public DoOnNext doOnNext() {
        return new DoOnNext();
    }

    @Bean
    public DoOnError doOnError() {
        return new DoOnError();
    }

    @Bean
    public DoOnComplete doOnComplete() {
        return new DoOnComplete();
    }
}
