package com.api.repository;

import com.api.model.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepositoryBase<Funcionario, Long> {

}
