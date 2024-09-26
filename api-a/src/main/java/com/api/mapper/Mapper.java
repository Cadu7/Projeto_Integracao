package com.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;

import com.api.model.Funcionario;
import com.api.model.FuncionarioDTO;

@ApplicationScoped
public class Mapper {

	public List<FuncionarioDTO> FuncToFuncDTO(List<Funcionario> lista) {
		lista.stream().map((e) -> new FuncionarioDTO()).collect(Collectors.toList());
		return lista.stream().map(FuncionarioDTO::new).collect(Collectors.toList());
	}

}
