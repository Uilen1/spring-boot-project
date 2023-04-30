package com.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.exception.lancamento.LancamentoNotFoundException;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.filter.LancamentoFilter;
import com.algamoney.api.service.LancamentoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Lancamento> listarLancamento(LancamentoFilter lancamentoFilter) {
		return lancamentoRepository.filtrar(lancamentoFilter);
	}

	@GetMapping("/{codigo}")
	public Lancamento buscarLancamentoPeloCodigo(@PathVariable Long codigo) {
		Optional<Lancamento> optionalLancamento = lancamentoRepository.findById(codigo);
		return optionalLancamento.orElseThrow(() -> new LancamentoNotFoundException(codigo));
	}

	@PostMapping
	public ResponseEntity<Lancamento> inserirLancamento(@Valid @RequestBody Lancamento lancamento,
			HttpServletResponse response) {
		Lancamento LancamentoSalva = lancamentoService.salvarLancamento(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, LancamentoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(LancamentoSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLancamentoPeloCodigo(@PathVariable Long codigo) {
		Optional<Lancamento> optionalLancamento = lancamentoRepository.findById(codigo);
		optionalLancamento.orElseThrow(() -> new LancamentoNotFoundException(codigo));
		lancamentoRepository.delete(optionalLancamento.get());
	}

}
