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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.exception.PessoaNotFoundException;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.PessoaService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/{codigo}")
	public Pessoa buscarPessoaPeloCodigo(@PathVariable Long codigo) {
		Optional<Pessoa> optionalPessoa = pessoaRepository.findById(codigo);
		return optionalPessoa.orElseThrow(() -> new PessoaNotFoundException(codigo));
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> alteraEndereco(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		return ResponseEntity.ok(pessoaService.atualizarEndereco(codigo, pessoa));
	}

	@PutMapping("/{codigo}/ativo")
	public ResponseEntity<Pessoa> alteraStatusCliente(@PathVariable Long codigo, @Valid @RequestBody Boolean status) {
		return ResponseEntity.ok(pessoaService.atualizarStatusPessoa(codigo, status));
	}

	@PostMapping
	public ResponseEntity<Pessoa> inserirPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePessoaPeloCodigo(@PathVariable Long codigo) {
		Optional<Pessoa> optionalPessoa = pessoaRepository.findById(codigo);
		optionalPessoa.orElseThrow(() -> new PessoaNotFoundException(codigo));
		pessoaRepository.delete(optionalPessoa.get());
	}

}
