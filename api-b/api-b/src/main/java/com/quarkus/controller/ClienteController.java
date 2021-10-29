package com.quarkus.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.quarkus.model.Cliente;
import com.quarkus.model.ClienteDTO;
import com.quarkus.service.ClienteService;

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
	public ClienteDTO buscarUm(@PathParam Long id) {
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
	public String delete(@PathParam Long id) {
		clienteService.delete(id);
		return "The document with the id " + id + " is deleted 👍";
	}

	@PUT
	@Path("/{id}")
	public ClienteDTO update(@PathParam Long id, Cliente cliente) {
		return clienteService.update(id, cliente);
	}

}
