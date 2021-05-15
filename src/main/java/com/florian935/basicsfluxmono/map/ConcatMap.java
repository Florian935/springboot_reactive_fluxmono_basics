package com.florian935.basicsfluxmono.map;

import reactor.core.publisher.Flux;

import static java.time.Duration.ofMillis;

/**
 * @author florian935
 */
public class ConcatMap {
    long delay = 5;

    public void concatMap() {
        final Flux<Integer> stream = Flux.just(1, 2, 3, 4, 5)
                .concatMap(this::delayReplyFor);

        stream.subscribe(System.out::println);
    }

    private Flux<Integer> delayReplyFor(Integer value) {
        final Flux<Integer> justOne = Flux.just(value).delayElements(ofMillis(delay * 1_000));
        delay--;

        return justOne;
    }
}
