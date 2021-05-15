package com.florian935.basicsfluxmono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.time.Duration;
import java.util.Random;

import static java.time.Duration.ofMillis;

@SpringBootApplication
public class FluxmonoApplication {
	final Random random = new Random();

	public static void main(String[] args) {
		SpringApplication.run(FluxmonoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void init() {
		basicGenerator();
	}

	private void basicGenerator() {
		final Flux<Integer> generator = Flux.<Integer>generate(sink -> sink.next(random.nextInt(10)))
				.delayElements(ofMillis(1_000))
				.doOnNext(randomNumber -> System.out.println("From Publisher ## " + randomNumber));

//		generator.subscribe();
		generator.subscribe(randomNumber -> System.out.println("From Subscriber ## " + randomNumber));
	}

	private void basicGeneratorWithError() {
		final Flux<Integer> generator = Flux
				.<Integer>generate(sink -> {
					final int randomNumber = random.nextInt(10);
					if (randomNumber != 5) {
						System.out.println("T==============================>");
						sink.next(randomNumber);
					} else {
						System.out.println("TOTOTOTOTOTOTOTOTOTOTOTOTO");
						sink.error(new Exception("HOOPS"));
					}
				})
				.delayElements(ofMillis(1_000))
				.doOnNext(randomNumber -> System.out.println("From Publisher ## " + randomNumber))
				.doOnError(error -> System.out.println("From Publisher ## " + error.getMessage()));


		generator.subscribe(
				randomNumber -> System.out.println("From Subscriber ## " + randomNumber),
				error -> System.out.println("From Subscriber ## " + error.getMessage())
		);
	}
}
