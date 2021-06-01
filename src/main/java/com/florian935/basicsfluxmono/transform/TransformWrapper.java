package com.florian935.basicsfluxmono.transform;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransformWrapper {

    @Bean
    public Handle handle() {
        return new Handle();
    }

    @Bean
    public As as() {
        return new As();
    }

    @Bean
    public Buffer buffer() {
        return new Buffer();
    }

    @Bean
    public Collect collect() {
        return new Collect();
    }
}