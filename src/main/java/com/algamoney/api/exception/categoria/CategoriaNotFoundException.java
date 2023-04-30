package com.algamoney.api.exception.categoria;

public class CategoriaNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public CategoriaNotFoundException(Long id) {
		super(String.format("Categoria com id %d não encontrada", id));
	}

}
