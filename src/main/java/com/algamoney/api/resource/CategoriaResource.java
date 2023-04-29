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
import com.algamoney.api.exception.CategoriaNotFoundException;
import com.algamoney.api.model.Categoria;
import com.algamoney.api.repository.CategoriaRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Categoria> listarCategoria() {
		return categoriaRepository.findAll();
	}

	@GetMapping("/{codigo}")
	public Categoria buscarCategoriaPeloCodigo(@PathVariable Long codigo) {
		Optional<Categoria> optionalCategoria = categoriaRepository.findById(codigo);
		return optionalCategoria.orElseThrow(() -> new CategoriaNotFoundException(codigo));
	}

	@PostMapping
	public ResponseEntity<Categoria> inserirCategoria(@Valid @RequestBody Categoria categoria,
			HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategoriaPeloCodigo(@PathVariable Long codigo) {
		Optional<Categoria> optionalCategoria = categoriaRepository.findById(codigo);
		optionalCategoria.orElseThrow(() -> new CategoriaNotFoundException(codigo));
		categoriaRepository.delete(optionalCategoria.get());
	}

}
