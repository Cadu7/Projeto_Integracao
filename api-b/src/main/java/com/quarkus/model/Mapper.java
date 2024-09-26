package com.quarkus.model;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class Mapper {

    public List<ClienteDTO> ListClienteToDTO(List<Cliente> listAll) {
        return listAll.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

}
