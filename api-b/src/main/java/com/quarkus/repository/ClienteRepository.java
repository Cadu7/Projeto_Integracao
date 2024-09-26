package com.quarkus.repository;

import com.quarkus.model.Cliente;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheMongoRepository<Cliente> {
}
