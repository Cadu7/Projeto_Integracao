package com.quarkus.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Mapper {

	public List<ClienteDTO> ListClienteToDTO(List<Cliente> listAll) {
		return listAll.stream().map(ClienteDTO::new).collect(Collectors.toList());
	}

}
