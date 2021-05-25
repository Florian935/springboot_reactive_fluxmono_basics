package com.florian935.basicsfluxmono.share;

import reactor.core.publisher.Flux;

import static java.time.Duration.ofMillis;

public class Cache {

    public void cache() {
        final Flux<Integer> stream = Flux.range(1, 50)
                .delayElements(ofMillis(500));

        stream.subscribe(value -> System.out.println("Subscriber 1 ## " + value));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stream.subscribe(value -> System.out.println("Subscriber 2 ## " + value));
    }
}
