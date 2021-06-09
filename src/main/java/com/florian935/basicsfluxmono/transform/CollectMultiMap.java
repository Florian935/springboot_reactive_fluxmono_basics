package com.florian935.basicsfluxmono.transform;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CollectMultiMap {

    public void collectMultiMap() {
        final Mono<Map<String, Collection<Integer>>> stream = Flux.just(1, 2, 3, 4, 5, 6)
                .collectMultimap(value -> value % 2 == 0 ? "Pair" : "Impair");

        stream.subscribe(System.out::println);
    }

    public void collectMultiMapValueExtractor() {
        final Mono<Map<String, Collection<Integer>>> stream = Flux.just(1, 2, 3, 4, 5, 6)
                .collectMultimap(value -> value % 2 == 0 ? "Pair" : "Impair", value -> value * 10);

        stream.subscribe(System.out::println);
    }

    public void collectMultiMapWithSupplier() {
        final Mono<Map<String, Collection<Integer>>> stream = Flux.just(1, 2, 3, 4, 5, 6)
                .collectMultimap(value -> value % 2 == 0 ? "Impair" : "Pair", value -> value * 10, HashMap::new);

        stream.subscribe(System.out::println);
    }
}
