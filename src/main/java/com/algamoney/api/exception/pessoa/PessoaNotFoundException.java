package com.algamoney.api.exception.pessoa;

public class PessoaNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public PessoaNotFoundException(Long id) {
		super(String.format("Pessoa com id %d não encontrada", id));
	}

}
