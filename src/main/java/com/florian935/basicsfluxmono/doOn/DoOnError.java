package com.florian935.basicsfluxmono.doOn;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * @author florian935
 */
@Component
public class DoOnError {

    public void doOnError() {
        final Flux<Integer> stream = Flux.just(1, 2, 3, 4, 5)
                .<Integer>handle((value, sink) -> sink.error(new Exception("ERROR HEHE !")))
                .doOnError(error -> System.out.println("From Publisher ## " + error.getMessage()));

        stream.subscribe(System.out::println, error -> System.out.println("From Subscriber ## " + error.getMessage()));
    }
}
