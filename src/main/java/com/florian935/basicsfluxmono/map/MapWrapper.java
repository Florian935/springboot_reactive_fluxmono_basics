package com.florian935.basicsfluxmono.map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author florian935
 */
@Configuration
public class MapWrapper {

    @Bean
    public ConcatMap concatMap() {
        return new ConcatMap();
    }

    @Bean
    public FlatMap flatMap() {
        return new FlatMap();
    }

    @Bean
    public FlatMapSequential flatMapSequential() {
        return new FlatMapSequential();
    }

    @Bean
    public Map map() {
        return new Map();
    }

    @Bean
    public SwitchMap switchMap() {
        return new SwitchMap();
    }
}
