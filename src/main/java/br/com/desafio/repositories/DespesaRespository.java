package br.com.desafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.model.Despesa;

public interface DespesaRespository extends JpaRepository<Despesa, Integer> {
	List<Despesa> findByDescricao(String despesa);
}
