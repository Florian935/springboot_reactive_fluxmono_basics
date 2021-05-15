package com.florian935.basicsfluxmono.source;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author florian935
 */
@Configuration
public class SourceWrapper {

    @Bean
    public FluxGenerator fluxGenerator() {
        return new FluxGenerator();
    }

    @Bean
    public FluxJust fluxJust() {
        return new FluxJust();
    }
}
