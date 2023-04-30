package com.algamoney.api.exception.lancamento;

public class LancamentoNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public LancamentoNotFoundException(Long id) {
		super(String.format("Lançamento com id %d não encontrado", id));
	}

}
