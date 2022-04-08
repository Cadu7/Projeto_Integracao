package com.api.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.api.model.Funcionario;
import com.api.model.FuncionarioDTO;
import com.api.service.FuncionarioService;

@Path("/funcionario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionarioController {

	@Inject
	private FuncionarioService funcService;

	@POST
	@Transactional
	public FuncionarioDTO inserir(Funcionario func) {
		return funcService.inserir(func);
	}

	@GET
	public List<FuncionarioDTO> buscarTodos() throws InterruptedException {
		Thread.sleep(3000);
		return funcService.buscarTodos();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void delete(@PathParam Long id) {
		funcService.delete(id);
	}

	@PUT
	@Transactional
	@Path("/{id}")
	public FuncionarioDTO update(Funcionario func, @PathParam Long id) {
		return funcService.update(func, id);
	}

}
