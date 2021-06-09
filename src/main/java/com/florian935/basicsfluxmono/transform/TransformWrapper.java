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

    @Bean
    public CollectList collectList() { return new CollectList(); }

    @Bean
    public CollectMap collectMap() { return new CollectMap(); }


    @Bean
    public CollectMultiMap collectMultiMap() { return new CollectMultiMap(); }

    @Bean
    public CollectSortedList collectSortedList() {
        return new CollectSortedList();
    }
}
