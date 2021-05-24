package com.florian935.basicsfluxmono.transform;

import reactor.core.publisher.Flux;

import java.util.List;

import static java.time.Duration.ofMillis;

public class Buffer {

    public void buffer() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5)
                .delayElements(ofMillis(1_000))
                .buffer();

        stream.subscribe(System.out::println);
    }

    public void bufferWithBufferingTimeSpan() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .delayElements(ofMillis(1_000))
                .buffer(ofMillis(3_000));

        stream.subscribe(System.out::println);
    }

    public void bufferWithBufferingTimeSpanAndOpenBufferEvery_timeSpanInferiorToOpenBuffer_missingValue() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .delayElements(ofMillis(1_000))
                .buffer(ofMillis(2_000), ofMillis(4_000));

        stream.subscribe(System.out::println);
    }

    public void bufferWithBufferingTimeSpanAndOpenBufferEvery_timeSpanSuperiorToOpenBuffer_overlapBuffer() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .delayElements(ofMillis(1_000))
                .buffer(ofMillis(4_000), ofMillis(2_000));

        stream.subscribe(System.out::println);
    }

    public void bufferWithBufferingTimeSpanAndOpenBufferEvery_timeSpanEqualToOpenBuffer_exactBuffer() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .delayElements(ofMillis(1_000))
                .buffer(ofMillis(2_000), ofMillis(2_000));

        stream.subscribe(System.out::println);
    }
}
