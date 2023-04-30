package com.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algamoney.api.exception.categoria.CategoriaNotFoundException;
import com.algamoney.api.model.Categoria;
import com.algamoney.api.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public Categoria buscarCategoriaPeloCodigo(Long codigo) {
		Optional<Categoria> optionalCategoria = categoriaRepository.findById(codigo);
		optionalCategoria.orElseThrow(() -> new CategoriaNotFoundException(codigo));
		return optionalCategoria.get();
	}

}
