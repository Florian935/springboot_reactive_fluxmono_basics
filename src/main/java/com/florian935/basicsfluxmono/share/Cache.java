package com.florian935.basicsfluxmono.share;

import reactor.core.publisher.Flux;

import static java.time.Duration.ofMillis;

public class Cache {

    public void cache() {
        final Flux<Integer> stream = Flux.range(1, 50)
                .delayElements(ofMillis(500))
                .cache();

        stream.subscribe(value -> System.out.println("Subscriber 1 ## " + value));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stream.subscribe(value -> System.out.println("Subscriber 2 ## " + value));
    }

    public void cacheDuration() {
        final Flux<Integer> stream = Flux.range(1, 50)
                .delayElements(ofMillis(1_000))
                .cache(ofMillis(500));

        stream.subscribe(value -> System.out.println("Subscriber 1 ## " + value));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stream.subscribe(value -> System.out.println("Subscriber 2 ## " + value));
    }

    public void cacheHistory() {
        Flux<Integer> stream = Flux.range(1, 10)
                .cache(3);

        stream.subscribe(value -> System.out.println("Subscriber 1 ## " + value));
        stream.subscribe(value -> System.out.println("Subscriber 2 ## " + value));
    }

    public void cacheHistoryDuration() {
        final Flux<Integer> stream = Flux.range(1, 50)
                .delayElements(ofMillis(1_000))
                .cache(3, ofMillis(500));

        stream.subscribe(value -> System.out.println("Subscriber 1 ## " + value));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stream.subscribe(value -> System.out.println("Subscriber 2 ## " + value));
    }
}
