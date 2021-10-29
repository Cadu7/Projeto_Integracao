package com.api.repository;

import javax.enterprise.context.ApplicationScoped;

import com.api.model.Funcionario;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepositoryBase<Funcionario, Long>{

}
