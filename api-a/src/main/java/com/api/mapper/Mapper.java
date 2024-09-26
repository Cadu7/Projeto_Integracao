package com.api.mapper;

import com.api.model.Funcionario;
import com.api.model.FuncionarioDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class Mapper {

    public List<FuncionarioDTO> FuncToFuncDTO(List<Funcionario> lista) {
        lista.stream().map((e) -> new FuncionarioDTO()).collect(Collectors.toList());
        return lista.stream().map(FuncionarioDTO::new).collect(Collectors.toList());
    }

}
