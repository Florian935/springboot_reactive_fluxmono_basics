package com.florian935.basicsfluxmono.filter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class All {

    public void all() {
        final Mono<Boolean> stream = Flux.just(2, 2, 2, 2, 2)
                .all(value -> value % 2 == 0);

        stream.subscribe(System.out::println);
    }
}
