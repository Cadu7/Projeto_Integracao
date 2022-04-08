package com.quarkus.service;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.springframework.cache.annotation.Cacheable;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.quarkus.model.Cliente;
import com.quarkus.model.ClienteDTO;
import com.quarkus.model.Mapper;
import com.quarkus.repository.ClienteRepository;

import io.quarkus.elytron.security.common.BcryptUtil;

@ApplicationScoped
public class ClienteService {

	@Inject
	private MongoClient mongoClient;
	@Inject
	private ClienteRepository clienteRepository;
	@Inject
	private Mapper mapper;

	@ConfigProperty(name = "database.name")
	private String dataBaseName;

	public MongoCollection<Document> getCollection() {
		return mongoClient.getDatabase(dataBaseName).getCollection("Novos");
	}

	// @Cacheable(value = "cache")
	public List<ClienteDTO> buscarTodos() {

		return mapper.ListClienteToDTO(clienteRepository.listAll());
//		List<Cliente> list = new ArrayList<>();
//		MongoCursor<Document> cursor = getCollection().find().iterator();
//		while (cursor.hasNext()) {
//			Document document = cursor.next();
//			Cliente cliente = new Cliente();
//			cliente.setBanco(document.getLong("banco"));
//			cliente.setNome(document.getString("nome"));
//			cliente.setNumConta(document.getLong("numConta"));
//			cliente.setPassword(document.getString("password"));
//			list.add(cliente);
//		}
//		cursor.close();
//		return list;
	}

	@Cacheable(value = "busca")
	public ClienteDTO buscarUm(Long id) {
		Bson filter = eq("_id", id);
		Document document = getCollection().find(filter).first();
		ClienteDTO cliente = new ClienteDTO();
		cliente.setBanco(document.getLong("banco"));
		cliente.setNome(document.getString("nome"));
		cliente.setNumConta(document.getLong("numConta"));
		return cliente;
	}

	public ClienteDTO inserir(Cliente cliente) {
		// clienteRepository.persist(cliente);
		Long leftLimit = 1000L;
		Long rightLimit = 9999L;
		Long l = 1555l;
		Long leftLimitId = 0l;
		Long rightLimitId = 100000000000000l;
		Document document = new Document()
				.append("_id", leftLimitId + (long) (Math.random() * (rightLimitId - leftLimitId))).append("banco", l)
				.append("nome", cliente.getNome().toUpperCase())
				.append("numConta", leftLimit + (long) (Math.random() * (rightLimit - leftLimit)))
				.append("password", BcryptUtil.bcryptHash(cliente.getPassword()));
		getCollection().insertOne(document);
		return new ClienteDTO(cliente);
	}

	public void delete(Long id) {
		clienteRepository.delete(new Document("_id", id));
		// getCollection().deleteOne(new Document("_id", id));
	}

	public ClienteDTO update(Long id, Cliente cliente) {
		Bson filter = eq("_id", id);

		if (cliente.getNome() != null) {
			getCollection().updateOne(filter, set("nome", cliente.getNome().toUpperCase()));
		}
		if (cliente.getPassword() != null) {
			getCollection().updateOne(filter, set("password", BcryptUtil.bcryptHash(cliente.getPassword())));
		}

		return new ClienteDTO(cliente);
	}

}