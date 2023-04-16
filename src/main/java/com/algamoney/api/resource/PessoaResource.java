package com.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.exception.PessoaNotFoundException;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/{codigo}")
	public Pessoa buscarPessoaPeloCodigo(@PathVariable Long codigo) {
		Optional<Pessoa> optionalPessoa= pessoaRepository.findById(codigo);
		return optionalPessoa.orElseThrow(() -> new PessoaNotFoundException(codigo));
	}

	@PostMapping
	public ResponseEntity<Pessoa> inserirPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);

	}

}
