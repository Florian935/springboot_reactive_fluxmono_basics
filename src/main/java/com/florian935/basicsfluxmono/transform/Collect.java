package com.florian935.basicsfluxmono.transform;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Collect {

    public void collect() {
        final Mono<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5)
                .collect(Collectors.toCollection(LinkedList::new));

        stream.subscribe(System.out::println);
    }

    public void collectWithBiConsumer() {
        final Mono<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5)
                .collect(ArrayList::new, List::add);

        stream.subscribe(System.out::println);
    }
}
