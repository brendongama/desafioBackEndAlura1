package br.com.desafio.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.model.Receita;
import br.com.desafio.repositories.ReceitaRepository;

@Service
public class ReceitaService { 
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	public Receita createReceita(Receita receita){		
		boolean temMesIgual = false;
		Receita receitaCriada = null;
		List<Receita> findDescricao = receitaRepository.findByDescricao(receita.getDescricao());
		if(findDescricao.isEmpty()) {
			receita.setData(LocalDate.now());
			receitaCriada = receitaRepository.save(receita);			
		}else {
			for(Receita listaReceita: findDescricao) {				
				if(listaReceita.getData().getMonthValue() == LocalDate.now().getMonthValue()) {
					temMesIgual = true;
				}
			}			
			if(!temMesIgual) {
				receita.setData(LocalDate.now());
				receitaCriada = receitaRepository.save(receita);	
			}
		}		
		return receitaCriada;		
	}
	
	public List<Receita> findAll() {
		return receitaRepository.findAll();
	}
	
	public Receita findById(Integer id) {
		Optional<Receita> receita = receitaRepository.findById(id);
		return receita.orElse(null);
	}
	
	public List<Receita> findByDescricao(String descricao){		
		return receitaRepository.findByDescricao(descricao);
	}
	
	public Receita update(Integer id, Receita receita) {
		receita.setId(id);		
		Receita antigaReceita = findById(id);
			receita.setData(antigaReceita.getData());
			antigaReceita = receita;
			return receitaRepository.save(antigaReceita);
	}
	
	public void delete(Integer id) {
		Receita receita = findById(id);
		receitaRepository.deleteById(receita.getId());		
	}

}
