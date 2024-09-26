package com.api.controller;

import com.api.model.Funcionario;
import com.api.model.FuncionarioDTO;
import com.api.service.FuncionarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

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
    public void delete(@PathParam("id") Long id) {
        funcService.delete(id);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public FuncionarioDTO update(Funcionario func, @PathParam("id") Long id) {
        return funcService.update(func, id);
    }

}
