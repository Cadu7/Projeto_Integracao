package com.api.controller;

import java.util.stream.Stream;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.api.model.Funcionario;
import com.api.service.FuncionarioRedisService;

@Path("/Redis")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioRedisController {

	@Inject
	private FuncionarioRedisService service;

	// this is not working
	@GET
	public Stream<Object> keys() {
		return service.keys();
	}

	@GET
	@Path("/{key}")
	public String get(@PathParam String key) {
		return service.get(key);
	}

	@POST
	@Path("/{key}")
	public void create(@PathParam String key, Funcionario func) {
		service.set_update(key, func);
	}

	@PUT
	@Path("/{key}")
	public void increment(@PathParam String key, Funcionario func) {
		service.set_update(key, func);
	}

	@DELETE
	@Path("/{key}")
	public void delete(@PathParam String key) {
		service.del(key);
	}

}
