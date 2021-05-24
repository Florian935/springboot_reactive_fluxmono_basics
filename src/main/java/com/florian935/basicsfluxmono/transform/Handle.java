package com.florian935.basicsfluxmono.transform;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Random;

import static java.time.Duration.ofMillis;

/**
 * @author florian935
 */
public class Handle {

    public void handle() {
        final var stream = Flux.just(1, 2, 3, 4, 5)
                .handle((value, sink) -> sink.next(value + 10))
                .delayElements(ofMillis(1_000));

        stream.subscribe(System.out::println);
    }

    public void handleWithError() {
        final var stream = Flux.just(1, 2, 3, 4, 5)
                .handle((value, sink) -> {
                    if (value == 3) {
                        sink.error(new Exception("HOOPS ERROR!"));
                    } else {
                        sink.next(value);
                    }
                });

        stream.subscribe(System.out::println, error -> System.out.println("From Subscriber ## " + error.getMessage()));
    }

    public void handleWithCompleteOrError() {
        final Random random = new Random();

        final var stream = Flux.just(1, 2, 3, 4, 5)
                .handle((value, sink) -> {
                    final int randomNumber = random.nextInt(10);
                    if (randomNumber == 3) {
                        sink.error(new Exception("HOOPS ERROR!"));
                    } else {
                        sink.next(value);
                    }
                    if (value == 5) {
                        sink.complete();
                    }
                });

        stream.subscribe(
                System.out::println,
                error -> System.out.println("From Subscriber ## " + error.getMessage()),
                () -> System.out.println("Stream completed !")
        );
    }
}
