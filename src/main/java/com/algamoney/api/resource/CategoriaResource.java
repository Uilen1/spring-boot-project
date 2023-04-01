package com.algamoney.api.resource;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algamoney.api.model.Categoria;
import com.algamoney.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
}
