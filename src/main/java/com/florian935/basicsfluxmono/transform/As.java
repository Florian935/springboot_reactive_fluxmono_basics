package com.florian935.basicsfluxmono.transform;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class As {

    public void as() {
        Mono<String> stream = Flux.just("a", "b", "c")
                .as(Mono::from);

        stream.subscribe(System.out::println);
    }
}
