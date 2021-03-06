package com.florian935.basicsfluxmono.fusion;

import reactor.core.publisher.Flux;

import java.util.List;

public class Concat {

    public void concat() {
        final Flux<Integer> stream = Flux.concat(
                Flux.just(1, 2, 3),
                Flux.just(10, 20, 30)
        );

        stream.subscribe(System.out::println);
    }

    public void concatPublisherOfPublisher() {
        final Flux<Integer> stream = Flux.concat(Flux.just(
                Flux.just(1, 2, 3),
                Flux.just(10, 20, 30)
        ));

        stream.subscribe(System.out::println);
    }

    public void concatFromIterable() {
        final Flux<Integer> stream = Flux.concat(List.of(
                Flux.just(1, 2, 3),
                Flux.just(10, 20, 30)
        ));

        stream.subscribe(System.out::println);
    }
}
