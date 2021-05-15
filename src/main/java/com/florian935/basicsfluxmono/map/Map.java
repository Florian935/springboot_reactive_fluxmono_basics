package com.florian935.basicsfluxmono.map;

import reactor.core.publisher.Flux;

public class Map {

    public void map() {
        final Flux<Integer> stream = Flux.just(1, 2, 3)
                .map(this::multiply);

        stream.subscribe(System.out::println);
    }

    private Integer multiply(Integer value) {
        return value * 2;
    }
}
