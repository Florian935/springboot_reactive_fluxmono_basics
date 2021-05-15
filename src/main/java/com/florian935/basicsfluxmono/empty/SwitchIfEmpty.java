package com.florian935.basicsfluxmono.empty;

import reactor.core.publisher.Flux;

/**
 * @author florian935
 */
public class SwitchIfEmpty {

    public void switchIfEmpty() {
        final Flux<String> stream = Flux.<String>empty()
                .switchIfEmpty(Flux.just("I'm a switched value because the precedent stream was empty !"));

        stream.subscribe(System.out::println);
    }
}
