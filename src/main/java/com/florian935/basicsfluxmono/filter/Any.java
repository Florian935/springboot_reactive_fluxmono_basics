package com.florian935.basicsfluxmono.filter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Any {

    public void any() {
        final Mono<Boolean> stream = Flux.just(1, 2, 3)
                .any(value -> value % 2 == 0);

        stream.subscribe(System.out::println);
    }
}
