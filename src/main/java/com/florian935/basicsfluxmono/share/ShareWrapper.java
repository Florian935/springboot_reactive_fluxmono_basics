package com.florian935.basicsfluxmono.share;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author florian935
 */
@Configuration
public class ShareWrapper {

    @Bean
    public Cache cache() {
        return new Cache();
    }
}
