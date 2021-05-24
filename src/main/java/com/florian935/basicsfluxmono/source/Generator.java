package com.florian935.basicsfluxmono.source;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Random;

import static java.time.Duration.ofMillis;

/**
 * @author florian935
 */
public class Generator {
    final Random random = new Random();

    public void generator() {
        final Flux<Integer> generator = Flux.<Integer>generate(sink -> sink.next(random.nextInt(10)))
                .delayElements(ofMillis(1_000))
                .doOnNext(randomNumber -> System.out.println("From Publisher ## " + randomNumber));

//		generator.subscribe();
        generator.subscribe(randomNumber -> System.out.println("From Subscriber ## " + randomNumber));
    }

    public void generatorWithError() {
        final Flux<Integer> generator = Flux
                .<Integer>generate(sink -> {
                    final int randomNumber = random.nextInt(10);
                    if (randomNumber != 5) {
                        sink.next(randomNumber);
                    } else {
                        sink.error(new Exception("HOOPS ERROR!"));
                    }
                })
                .delayElements(ofMillis(1_000))
                .doOnNext(randomNumber -> System.out.println("From Publisher ## " + randomNumber))
                .doOnError(error -> System.out.println("From Publisher ## " + error.getMessage()));


//		generator.subscribe();
//		generator.subscribe(randomNumber -> System.out.println("From Subscriber ## " + randomNumber);
        generator.subscribe(
                randomNumber -> System.out.println("From Subscriber ## " + randomNumber),
                error -> System.out.println("From Subscriber ## " + error.getMessage())
        );
    }

    public void generatorCompleteOrError() {
        final Flux<Integer> generator = Flux
                .<Integer>generate(sink -> {
                    final int randomNumber = random.nextInt(10);
                    if (randomNumber == 4) {
                        sink.complete();
                    } else if (randomNumber == 5) {
                        sink.error(new Exception("HOOPS ERROR!"));
                    } else {
                        sink.next(randomNumber);
                    }
                })
                .delayElements(ofMillis(1_000))
                .doOnNext(randomNumber -> System.out.println("From Publisher ## " + randomNumber))
                .doOnError(error -> System.out.println("From Publisher ## " + error.getMessage()))
                .doOnComplete(() -> System.out.println("From Publisher ## " + " STREAM COMPLETED !"));

        generator.subscribe(
                randomNumber -> System.out.println("From Subscriber ## " + randomNumber),
                error -> System.out.println("From Subscriber ## " + error.getMessage()),
                () -> System.out.println("From Subscriber ## " + " STREAM COMPLETED !")
        );
    }
}
