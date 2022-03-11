package br.com.desafio.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
	
	List<Receita> findByDescricao(String descricao);

	Receita save(Optional<Receita> antigaReceita); 

}
