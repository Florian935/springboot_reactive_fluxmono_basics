package com.florian935.basicsfluxmono.transform;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class CollectMap {

    public void collectMap() {
        final Mono<Map<Character, Integer>> stream = Flux.just(1, 2, 3, 4, 5)
        .collectMap(value -> (char)(value - 1 + 'A'));

        stream.subscribe(System.out::println);
    }

    public void collectMapWithValueExtractor() {
        final Mono<Map<Character, Integer>> stream = Flux.just(1, 2, 3, 4, 5)
                .collectMap(value -> (char)(value - 1 + 'A'), value -> value * 10);

        stream.subscribe(System.out::println);
    }
}
