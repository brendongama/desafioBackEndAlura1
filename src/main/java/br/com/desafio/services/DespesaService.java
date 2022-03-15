package br.com.desafio.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.model.Categoria;
import br.com.desafio.model.Despesa;
import br.com.desafio.repositories.DespesaRespository;

@Service
public class DespesaService { 
	
	@Autowired
	private DespesaRespository despesaRespository;
	
	public Despesa createDespesa(Despesa despesa){		
		boolean temMesIgual = false;
		Despesa despesaCriada = null;
		List<Despesa> findDescricao = despesaRespository.findByDescricao(despesa.getDescricao());
		if(findDescricao.isEmpty()) {
			despesa.setData(LocalDate.now());
			if(null == despesa.getCategoria()) {
				despesa.setCategoria(Categoria.OUTRAS);
			}
			despesaCriada = despesaRespository.save(despesa);			
		}else {
			for(Despesa listaDespesa: findDescricao) {				
				if(listaDespesa.getData().getMonthValue() == LocalDate.now().getMonthValue()) {
					temMesIgual = true;
				}
			}			
			if(!temMesIgual) {
				despesa.setData(LocalDate.now());
				despesaCriada = despesaRespository.save(despesa);	
			}
		}		
		return despesaCriada;		
	}
	
	public List<Despesa> findAll() {
		return despesaRespository.findAll();
	}
	
	public Despesa findById(Integer id) {
		Optional<Despesa> despesa = despesaRespository.findById(id);
		return despesa.orElse(null);
	}
	
	public List<Despesa> findByDescricao(String descricao){		
		return despesaRespository.findByDescricao(descricao);
	}
	
	public Despesa update(Integer id, Despesa despesa) {
		despesa.setId(id);		
		Despesa antigaDespesa = findById(id);
			despesa.setData(antigaDespesa.getData());
			antigaDespesa = despesa;
			return despesaRespository.save(antigaDespesa);
	}
	
	public void delete(Integer id) {
		Despesa despesa = findById(id);
		despesaRespository.deleteById(despesa.getId());		
	}

}
