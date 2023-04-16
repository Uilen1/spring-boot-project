package com.algamoney.api.event.listener;

import java.net.URI;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algamoney.api.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent event) {
		adicionarHeaderLocation(event);
	}

	private void adicionarHeaderLocation(RecursoCriadoEvent event) {
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequestUri()
				.path("/{codigo}")
				.buildAndExpand(event.getCodigo())
				.toUri();
		
		event.getResponse().setHeader("Location", uri.toASCIIString());
		
	}

}
