package com.florian935.basicsfluxmono.map;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static java.time.Duration.ofMillis;

/**
 * @author florian935
 */
public class SwitchMap {

    public void switchMap() {
        final Flux<String> stream = Flux.just("Never emitted ...", "And never emitted too ...", "Emitted !")
                .delayElements(ofMillis(500))
                .switchMap(this::replyWithDelay);

        stream.subscribe(System.out::println);
    }

    private Flux<String> replyWithDelay(String value) {
        return Flux.just(value).delayElements(ofMillis(1_000));
    }
}
