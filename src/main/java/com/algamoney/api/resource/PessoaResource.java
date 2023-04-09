package com.algamoney.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algamoney.api.exception.CategoriaNotFoundException;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Pessoa> listarCategoria(){
		return pessoaRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public Pessoa buscarCategoriaPeloCodigo(@PathVariable Long codigo){
		Optional<Pessoa> optionalCategoria = pessoaRepository.findById(codigo);
		return optionalCategoria.orElseThrow(()-> new CategoriaNotFoundException(codigo));
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> inserirCategoria(@Valid @RequestBody Pessoa categoria, HttpServletResponse response){
		Pessoa categoriaSalva = pessoaRepository.save(categoria);
		
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("/{codigo}")
					.buildAndExpand(categoriaSalva.getCodigo())
					.toUri();
		
		return ResponseEntity.created(uri).body(categoriaSalva);
		
	}

}
