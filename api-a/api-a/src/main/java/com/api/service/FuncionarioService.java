package com.api.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.api.mapper.Mapper;
import com.api.model.Funcionario;
import com.api.model.FuncionarioDTO;
import com.api.producer.OrderProducer;
import com.api.repository.FuncionarioRepository;

import io.quarkus.elytron.security.common.BcryptUtil;

@ApplicationScoped
@Transactional
public class FuncionarioService {

	@Inject
	private FuncionarioRepository funcRepository;

	@Inject
	private OrderProducer orderProducer;

	@Inject
	private Mapper mapper;

	public FuncionarioDTO inserir(Funcionario func) {
		try {
			func.setPassword(BcryptUtil.bcryptHash(func.getPassword()));

			funcRepository.persist(func);
			orderProducer.publisher(func);

			return new FuncionarioDTO(func);
		} catch (Exception e) {

		}
		return null;
	}

	public List<FuncionarioDTO> buscarTodos() {
		return mapper.FuncToFuncDTO(funcRepository.listAll());
	}

	public void delete(Long id) {
		funcRepository.deleteById(id);
	}

	public FuncionarioDTO update(Funcionario func, Long id) {
		func.setPassword(BcryptUtil.bcryptHash(func.getPassword()));
		funcRepository.update("UPDATE FROM Funcionario SET nome = ?1, password = ?2 where id = ?3", func.getNome(),
				func.getPassword(), id);
		return new FuncionarioDTO(func);
	}

}