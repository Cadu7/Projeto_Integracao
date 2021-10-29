package com.quarkus.model;

import javax.json.bind.annotation.JsonbTransient;

public class ClienteDTO {

	private String nome;
	private String password;
	private Long numConta;
	private Long banco;

	public ClienteDTO(String nome, String password, Long numConta, Long banco) {
		super();
		this.nome = nome;
		this.password = password;
		this.numConta = numConta;
		this.banco = banco;
	}

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Cliente cliente) {
		super();
		this.nome = cliente.getNome();
		this.password = cliente.getPassword();
		this.numConta = cliente.getNumConta();
		this.banco = cliente.getBanco();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonbTransient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getNumConta() {
		return numConta;
	}

	public void setNumConta(Long numConta) {
		this.numConta = numConta;
	}

	public Long getBanco() {
		return banco;
	}

	public void setBanco(Long banco) {
		this.banco = banco;
	}

}
