package com.florian935.basicsfluxmono.error;

import reactor.core.publisher.Flux;

/**
 * @author florian935
 */
public class OnErrorMap {

    public void onErrorMap() {
        final Flux<Integer> stream = Flux.just(1, 2, 3, 4, 5)
                .<Integer>handle((value, sink) -> sink.error(new Exception("HOOPS ERROR !")))
                .onErrorMap((error) ->
                        new Exception("HOOPS ERROR, apply a function to the error by changing the message !")
                );

        stream.subscribe(
                System.out::println,
                error -> System.out.println("From Subscriber ## " + error.getMessage())
        );
    }

    public void onErrorMapFilterErrorType() {
        final Flux<Integer> stream = Flux.just(2, 4, 6)
                .<Integer>handle((value, sink) -> {
                    if (value % 2 == 0) {
                        sink.error(new IllegalArgumentException("HOOPS ! Illegal Argument Exception"));
                    } else {
                        sink.error(new ArithmeticException("HOOPS ! Arithmetic Exception !"));
                    }
                })
                .onErrorMap(
                        IllegalArgumentException.class,
                        error -> new UnsupportedOperationException("Change error type with Unsupported Operation Exception")
                )
                .onErrorMap(
                        ArithmeticException.class,
                        error -> new ArrayIndexOutOfBoundsException("Change error type with Array Index Out Of Bounds Exception!")
                );

        stream.subscribe(
                System.out::println,
                error -> System.out.println("From Subscriber ## " + error.getMessage())
        );
    }
}
