package com.florian935.basicsfluxmono.transform;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CollectList {

    public void collectList() {
        final Mono<List<String>> stream = Flux.just("A", "B", "C", "D", "E")
                .collectList();

        stream.subscribe(System.out::println);
    }
}
