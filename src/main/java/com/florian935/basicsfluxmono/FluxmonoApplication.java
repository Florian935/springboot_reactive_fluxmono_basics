package com.florian935.basicsfluxmono;

import com.florian935.basicsfluxmono.source.FluxGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class FluxmonoApplication {
	private final FluxGenerator fluxGenerator;

	FluxmonoApplication(FluxGenerator fluxGenerator) {
		this.fluxGenerator = fluxGenerator;
	}

	public static void main(String[] args) {
		SpringApplication.run(FluxmonoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void init() {
		fluxGenerator.basicGeneratorCompleteOrError();
	}


}
