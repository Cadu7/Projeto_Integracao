package com.api.model;

public class FuncionarioDTO {

	private String nome;

	public FuncionarioDTO(String nome) {
		super();
		this.nome = nome;
	}

	public FuncionarioDTO() {
		super();
	}

	public FuncionarioDTO(Funcionario func) {
		super();
		this.nome = func.getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
