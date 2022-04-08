package com.quarkus.repository;

import javax.enterprise.context.ApplicationScoped;

import com.quarkus.model.Cliente;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class ClienteRepository implements PanacheMongoRepository<Cliente>{
}
