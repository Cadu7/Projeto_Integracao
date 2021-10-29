package com.api.controller;

import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;
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
