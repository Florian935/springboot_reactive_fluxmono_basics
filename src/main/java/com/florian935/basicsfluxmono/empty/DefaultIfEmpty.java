package com.florian935.basicsfluxmono.empty;

import reactor.core.publisher.Flux;

/**
 * @author florian935
 */
public class DefaultIfEmpty {

    public void defaultIfEmpty() {
        final Flux<String> stream = Flux.<String>empty()
                .defaultIfEmpty("I'm an empty flux and I return this default value !");

        stream.subscribe(System.out::println);
    }
}
