package com.algamoney.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algamoney.api.exception.pessoa.PessoaInexistenteOuInativaException;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	PessoaService pessoaService;

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	LancamentoRepository lancamentoRepository;

	public Lancamento salvarLancamento(Lancamento lancamento) {
		Pessoa pessoa = pessoaService.buscarPessoaPeloCodigo(lancamento.getPessoa().getCodigo());
		categoriaService.buscarCategoriaPeloCodigo(lancamento.getCategoria().getCodigo());
		if (pessoa == null || pessoa.getAtivo() == false) {
			throw new PessoaInexistenteOuInativaException(lancamento.getPessoa().getCodigo());
		}
		return lancamentoRepository.save(lancamento);
	}

}
