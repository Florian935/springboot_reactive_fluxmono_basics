package com.florian935.basicsfluxmono.error;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author florian935
 */
public class OnErrorResume {

    public void onErrorResume() {
        final Flux<Integer> stream = Flux.just(2, 4, 6, 7)
                .<Integer>handle((value, sink) -> {
                    if (value % 2 == 0) {
                        sink.next(value);
                    } else {
                        sink.error(new Exception("HOOPS Error ! Not divisible by 2"));
                    }
                })
                .onErrorResume(error -> Flux.just(8, 10, 11));

        stream.subscribe(System.out::println);
    }

    public void onErrorResumeFilterErrorType() {
        final Flux<Integer> stream = Flux.just(2, 4, 6, 7)
                .<Integer>handle((value, sink) -> {
                    if (value % 2 == 0) {
                        sink.error(new IllegalArgumentException("HOOPS ! Illegal Argument Exception"));
                    } else {
                        sink.error(new ArithmeticException("HOOPS ! Arithmetic Exception !"));
                    }
                })
                .onErrorResume(IllegalArgumentException.class, error -> Flux.just(10_000, 10_001, 10_002))
                .onErrorResume(ArithmeticException.class, error -> Flux.just(20_000, 20_001, 20_002));

        stream.subscribe(System.out::println);
    }

    public void onErrorResumeConcreteUseCaseHttpResponse() {
        final Mono<Integer> stream = Mono.just(2)
                .<Integer>handle((value, sink) -> {
                    if (value % 2 == 0) {
                        sink.error(new IllegalArgumentException("HOOPS ! Illegal Argument Exception"));
                    } else {
                        sink.next(value);
                    }
                })
                // Here, we can send an ServerResponse.notFound() or other error response
                .onErrorResume(error -> Mono.error(new HttpClientErrorException(BAD_REQUEST, "Bad Request")));

        stream.subscribe(
                System.out::println,
                error -> {
                    final String errorMessage = String.format(
                            "From Subscriber ## I'm the client and I get an appropriate HTTP status error ## %s",
                            error.getMessage()
                    );
                    System.out.println(errorMessage);
                }
        );
    }
}
