package com.quarkus.model;

import javax.json.bind.annotation.JsonbTransient;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "Client")
public class Cliente {

	private Long id;
	private String nome;
	private String password;
	private Long numConta;
	private Long banco;

	public Cliente(Long id, String nome, String password, Long numConta, Long banco) {
		super();
		this.id = id;
		this.nome = nome;
		this.password = password;
		this.numConta = numConta;
		this.banco = banco;
	}

	public Cliente() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", password=" + password + ", numConta=" + numConta + ", banco="
				+ banco + "]";
	}

}
