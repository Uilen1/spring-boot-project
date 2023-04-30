package com.algamoney.api.exception.pessoa;

public class PessoaInexistenteOuInativaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PessoaInexistenteOuInativaException(Long id) {
		super(String.format("Pessoa com id %d está inativa", id));
	}
	
	

}
