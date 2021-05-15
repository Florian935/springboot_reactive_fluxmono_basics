package com.florian935.basicsfluxmono.doOn;

import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

/**
 * @author florian935
 */
public class DoOnNext {

    public void doOnNext() {
        final Flux<Integer> stream = Flux.just(1, 2, 3, 4, 5)
                .doOnNext(System.out::println);

        stream.subscribe();
    }
}
