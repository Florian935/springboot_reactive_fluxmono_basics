package com.florian935.basicsfluxmono.fusion;

import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import static java.time.Duration.ofMillis;

/**
 * @author florian935
 */
public class Zip {

    public void zip() {
        final Flux<String> stream = Flux.just("A", "B", "C").repeat();
        final Flux<Long> interval = Flux.interval(ofMillis(1_000));

        final Flux<String> mergeStreamWithInterval = Flux.zip(stream, interval)
                .map(this::formatOutputInString);

        mergeStreamWithInterval.subscribe(System.out::println);
    }

    private String formatOutputInString(Tuple2<String, Long> tuple2) {
        return new StringBuilder(tuple2.getT1()).append(tuple2.getT2()).toString();
    }

    public void zipWithIntegratedOutput() {
        final Flux<String> stream = Flux.just("A", "B", "C").repeat();
        final Flux<Long> interval = Flux.interval(ofMillis(1_000));

        final Flux<String> mergeStreamWithInterval = Flux.zip(stream, interval, this::formatOutputCombinatorInString);

        mergeStreamWithInterval.subscribe(System.out::println);
    }

    private String formatOutputCombinatorInString(String stream, Long interval) {
        return new StringBuilder(stream).append(interval).toString();
    }
}
