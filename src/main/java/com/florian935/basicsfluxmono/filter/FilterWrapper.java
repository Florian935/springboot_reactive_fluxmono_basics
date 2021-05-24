package com.florian935.basicsfluxmono.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author florian935
 */
@Configuration
public class FilterWrapper {

    @Bean
    public All all() {
        return new All();
    }

    @Bean
    public Any any() {
        return new Any();
    }
}
