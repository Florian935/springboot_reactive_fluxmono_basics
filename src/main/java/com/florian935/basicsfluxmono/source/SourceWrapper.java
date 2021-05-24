package com.florian935.basicsfluxmono.source;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author florian935
 */
@Configuration
public class SourceWrapper {

    @Bean
    public Generator fluxGenerator() {
        return new Generator();
    }

    @Bean
    public Just fluxJust() {
        return new Just();
    }
}
