package com.florian935.basicsfluxmono;

import com.florian935.basicsfluxmono.doOn.DoOnWrapper;
import com.florian935.basicsfluxmono.empty.EmptyWrapper;
import com.florian935.basicsfluxmono.error.ErrorWrapper;
import com.florian935.basicsfluxmono.filter.FilterWrapper;
import com.florian935.basicsfluxmono.fusion.FusionWrapper;
import com.florian935.basicsfluxmono.map.MapWrapper;
import com.florian935.basicsfluxmono.source.SourceWrapper;
import com.florian935.basicsfluxmono.transform.TransformWrapper;
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
	private final TransformWrapper transformWrapper;
	private final DoOnWrapper doOnWrapper;
	private final MapWrapper mapWrapper;
	private final EmptyWrapper emptyWrapper;
	private final ErrorWrapper errorWrapper;
	private final FusionWrapper fusionWrapper;
	private final FilterWrapper filterWrapper;

	public static void main(String[] args) {
		SpringApplication.run(FluxmonoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void init() {
		transformWrapper.buffer().bufferWithBufferingTimeSpanAndOpenBufferEvery_timeSpanEqualToOpenBuffer_exactBuffer();
	}
}
