package com.florian935.basicsfluxmono.empty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author florian935
 */
@Configuration
public class EmptyWrapper {

    @Bean
    public DefaultIfEmpty defaultIfEmpty() {
        return new DefaultIfEmpty();
    }

    @Bean
    public SwitchIfEmpty switchIfEmpty() {
        return new SwitchIfEmpty();
    }
}
