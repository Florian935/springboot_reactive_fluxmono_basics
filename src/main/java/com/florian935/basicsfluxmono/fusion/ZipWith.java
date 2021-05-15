package com.florian935.basicsfluxmono.fusion;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import static java.time.Duration.ofMillis;

/**
 * @author florian935
 */
public class ZipWith {
    public void zip() {
        final Flux<Long> interval = Flux.interval(ofMillis(1_000));
        final Flux<String> stream = Flux.just("A", "B", "C")
                .repeat()
                .zipWith(interval)
                .map(this::formatZipOutput);

        stream.subscribe(System.out::println);
    }

    private String formatZipOutput(Tuple2<String, Long> tuple2) {
        return new StringBuilder(tuple2.getT1()).append(tuple2.getT2()).toString();
    }

    public void zipWithintegratedOutput() {
        final Flux<Long> interval = Flux.interval(ofMillis(1_000));
        final Flux<String> stream = Flux.just("A", "B", "C")
                .repeat()
                .zipWith(interval, this::formatZipIntegratedOutput);

        stream.subscribe(System.out::println);
    }

    private String formatZipIntegratedOutput(String stream, Long interval) {
        return new StringBuilder(stream).append(interval).toString();
    }
}