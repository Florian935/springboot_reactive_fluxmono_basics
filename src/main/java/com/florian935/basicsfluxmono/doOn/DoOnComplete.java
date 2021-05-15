package com.florian935.basicsfluxmono.doOn;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import static java.time.Duration.ofMillis;

/**
 * @author florian935
 */
public class DoOnComplete {

    public void doOnComplete() {
        final Flux<Integer> stream = Flux.interval(ofMillis(1_000))
                .<Integer>handle((value, sink) -> sink.complete())
                .doOnComplete(() -> System.out.println("From Publisher ## Stream Completed !"));

        stream.subscribe(
                System.out::println,
                System.out::println,
                () -> System.out.println("From Subscriber ## Stream completed")
        );
    }
}
