package com.algamoney.api.model;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Endereco {

	@NotNull(message = "Logradouro é obrigatório")
	@NotBlank
	String logradouro;
	@NotNull(message = "Numero da residencia é obrigatório")
	@NotBlank
	String numero;
	@NotNull(message = "Complemento é obrigatório")
	@NotBlank
	String complemento;
	@NotNull(message = "Bairro é obrigatório")
	@NotBlank
	String bairro;
	@NotNull(message = "CEP é obrigatório")
	@NotBlank
	String cep;
	@NotNull(message = "Cidade é obrigatório")
	@NotBlank
	String cidade;
	@NotNull(message = "Estado é obrigatório")
	@NotBlank
	String estado;
	
	

	public Endereco() {
		super();
	}

	public Endereco(String logradouro, String numero, String complemento, String bairro, String cep, String cidade,
			String estado) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cep, cidade, complemento, estado, logradouro, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
				&& Objects.equals(cidade, other.cidade) && Objects.equals(complemento, other.complemento)
				&& Objects.equals(estado, other.estado) && Objects.equals(logradouro, other.logradouro)
				&& Objects.equals(numero, other.numero);
	}
	
	

}
