package com.florian935.basicsfluxmono.error;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.Objects;

/**
 * @author florian935
 */
public class OnErrorReturn {

    public void onErrorReturn() {
        final Flux<Integer> stream = Flux.just(1, 2, 3, 4, 5)
                .<Integer>handle((value, sink) -> sink.error(new Exception("Hoops ! Error !")))
                .onErrorReturn(404);

        stream.subscribe(
                System.out::println,
                error -> System.out.println("From Subscriber ## " + error.getMessage())
        );
    }

    public void onErrorReturnFilterErrorType() {
        final Flux<Integer> stream = Flux.just(2, 4, 6)
                .<Integer>handle((value, sink) -> {
                    if (value % 2 == 0) {
                        sink.error(new IllegalAccessException("Hoops ! Illegal Access Exception !"));
                    } else {
                        sink.error(new IllegalArgumentException("Hoops ! Illegal Argument Exception !"));
                    }
                })
                .onErrorReturn(IllegalAccessException.class, 404)
                .onErrorReturn(IllegalArgumentException.class, 401);
        stream.subscribe(
                System.out::println,
                error -> System.out.println("From Subscriber ## " + error.getMessage())
        );
    }
}
