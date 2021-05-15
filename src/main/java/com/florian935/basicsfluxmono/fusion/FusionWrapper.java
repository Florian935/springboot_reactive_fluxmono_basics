package com.florian935.basicsfluxmono.fusion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author florian935
 */
@Configuration
public class FusionWrapper {

    @Bean
    public Zip zip() {
        return new Zip();
    }

    @Bean
    public ZipWith zipWith() {
        return new ZipWith();
    }
}
