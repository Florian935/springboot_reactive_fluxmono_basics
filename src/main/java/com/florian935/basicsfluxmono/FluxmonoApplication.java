package com.florian935.basicsfluxmono;

import com.florian935.basicsfluxmono.doOn.DoOnComplete;
import com.florian935.basicsfluxmono.doOn.DoOnError;
import com.florian935.basicsfluxmono.doOn.DoOnNext;
import com.florian935.basicsfluxmono.doOn.DoOnWrapper;
import com.florian935.basicsfluxmono.source.FluxGenerator;
import com.florian935.basicsfluxmono.source.SourceWrapper;
import com.florian935.basicsfluxmono.transform.FluxHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * @author florian935
 */
@SpringBootApplication
@RequiredArgsConstructor
public class FluxmonoApplication {
	private final SourceWrapper sourceWrapper;
	private final FluxHandle fluxHandle;
	private final DoOnWrapper doOnWrapper;

	public static void main(String[] args) {
		SpringApplication.run(FluxmonoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void init() {
		sourceWrapper.fluxGenerator().generator();
	}


}
