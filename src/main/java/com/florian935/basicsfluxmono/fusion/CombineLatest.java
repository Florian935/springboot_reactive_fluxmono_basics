package com.florian935.basicsfluxmono.fusion;

import reactor.core.publisher.Flux;

import static java.time.Duration.ofMillis;

public class CombineLatest {

    public void combineLatest() {
        Flux<String> stream = Flux.combineLatest(
                letters(), numbers(), (letter, number) -> String.format("%s%d", letter, number));

        stream.subscribe(System.out::println);
    }

    private Flux<String> letters() {
        return Flux.just("A", "B", "C", "D", "E", "F")
                .delayElements(ofMillis(1_000));
    }

    private Flux<Integer> numbers() {
        return Flux.just(1, 2, 3)
                .delayElements(ofMillis(4_500));
    }
}
