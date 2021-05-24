package com.florian935.basicsfluxmono.source;

import reactor.core.publisher.Flux;

/**
 * @author florian935
 */
public class Just {

    public void just() {
        final Flux<Character> stream = Flux.just('a', 'b', 'c', 'd');

        stream.subscribe(System.out::println);
    }
}
