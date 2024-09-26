package com.quarkus.controller;

import com.quarkus.model.Cliente;
import com.quarkus.model.ClienteDTO;
import com.quarkus.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/cliente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    private ClienteService clienteService;

    @GET
    @Path("/todos")
    public List<ClienteDTO> buscarTodos() {
        return clienteService.buscarTodos();
    }

    @GET
    @Path("/{id}")
    public ClienteDTO buscarUm(@PathParam("id") Long id) {
        return clienteService.buscarUm(id);
    }

    @POST
    public ClienteDTO inserir(Cliente cliente) {
        return clienteService.inserir(cliente);
    }

    @POST
    @Path("/loop")
    public ClienteDTO inserirvarios(Cliente cliente) {
        for (int i = 0; i < 15000000; i++) {
            clienteService.inserir(cliente);
            clienteService.inserir(cliente);
            clienteService.inserir(cliente);
            clienteService.inserir(cliente);
        }

        return clienteService.inserir(cliente);
    }

    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") Long id) {
        clienteService.delete(id);
        return "The document with the id " + id + " is deleted 👍";
    }

    @PUT
    @Path("/{id}")
    public ClienteDTO update(@PathParam("id") Long id, Cliente cliente) {
        return clienteService.update(id, cliente);
    }

}
