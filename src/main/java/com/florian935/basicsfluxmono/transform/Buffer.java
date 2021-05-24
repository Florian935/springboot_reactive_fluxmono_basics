package com.florian935.basicsfluxmono.transform;

import com.florian935.basicsfluxmono.model.Employee;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

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

    public void bufferWithBufferingTimeSpanAndOpenBufferEvery_timeSpanInferiorToOpenBuffer_droppingBuffer() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .delayElements(ofMillis(1_000))
                .buffer(ofMillis(2_000), ofMillis(4_000));

        stream.subscribe(System.out::println);
    }

    public void bufferWithBufferingTimeSpanAndOpenBufferEvery_timeSpanSuperiorToOpenBuffer_overlappingBuffer() {
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

    public void bufferMaxSize() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .buffer(2);

        stream.subscribe(System.out::println);
    }

    public void bufferMaxSizeSkip_maxSizeInferiorToSkip_droppingBuffer() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .buffer(2, 3);

        stream.subscribe(System.out::println);
    }

    public void bufferMaxSizeSkip_maxSizeSuperiorToSkip_overlappingBuffer() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .buffer(3, 2);

        stream.subscribe(System.out::println);
    }

    public void bufferMaxSizeSkip_maxSizeEqualToSkip_exactBuffer() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .buffer(2, 2);

        stream.subscribe(System.out::println);
    }

    public void bufferCompanionPublisher() {
        final Flux<Long> interval = Flux.interval(ofMillis(2_000));
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .delayElements(ofMillis(500))
                .buffer(interval);

        stream.subscribe(System.out::println);
    }

    public void bufferTimeout() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .delayElements(ofMillis(1_000))
                .bufferTimeout(3, ofMillis(2_000));

        stream.subscribe(System.out::println);
    }

    public void bufferUntil() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .bufferUntil(value -> value % 5 == 0);

        stream.subscribe(System.out::println);
    }

    public void bufferUntilCutBefore() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .bufferUntil(value -> value % 5 == 0, true);

        stream.subscribe(System.out::println);
    }

    public void bufferUntilChanged() {
        final Flux<List<Integer>> stream = Flux.just(1, 2, 2, 3, 4, 4, 4, 5, 5)
                .bufferUntilChanged();

        stream.subscribe(System.out::println);
    }

    public void bufferUntilChangedKeySelector() {
        final Flux<List<Employee>> stream = Flux.fromStream(getEmployees())
                .bufferUntilChanged(Employee::getAge);

        stream.subscribe(System.out::println);
    }

    public void bufferUntilChangedKeySelectorWithPredicate() {
        final Flux<List<Employee>> stream = Flux.fromStream(getEmployees())
                .bufferUntilChanged(Employee::getAge, (a, b) -> a > b);

        stream.subscribe(System.out::println);
    }

    public void bufferWhen() {
        final Flux<Long> interval = Flux.interval(ofMillis(100));
        final Flux<List<Integer>> stream = Flux.range(1, 200)
                .delayElements(ofMillis(25))
                .bufferWhen(interval, Flux::just);

        stream.subscribe(System.out::println);
    }

    private Stream<Employee> getEmployees() {
        return Stream.of(
                new Employee("toto", 10),
                new Employee("toto1", 10),
                new Employee("toto2", 35),
                new Employee("toto3", 40),
                new Employee("toto4", 40),
                new Employee("toto5", 18),
                new Employee("toto6", 18)
        );
    }
}
