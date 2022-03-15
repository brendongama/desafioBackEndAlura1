package br.com.desafio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.desafio.model.Receita;
import br.com.desafio.services.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {
	
	private static final String ID = "/{id}";
	
	@Autowired
	private ReceitaService receitaService;

	@PostMapping
	public ResponseEntity<Receita> create(@RequestBody Receita receita) {
		Receita novaReceita = receitaService.createReceita(receita);		
		if(novaReceita != null ) {
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest().path(ID).buildAndExpand(novaReceita.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Receita>> findAll(@RequestParam(required = false) String descricao){
		List<Receita> receitas;
		if(null == descricao) {
			receitas = receitaService.findAll();
		}else {
			receitas = receitaService.findByDescricao(descricao);
		}		
		return ResponseEntity.ok().body(receitas);
	} 
	 
	@GetMapping(value = "descricao/{descricao}")
	public ResponseEntity<List<Receita>> findByDescricao(@PathVariable String descricao){
		List<Receita> receitasDescricao = receitaService.findByDescricao(descricao);
		return ResponseEntity.ok().body(receitasDescricao);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Receita> findById(@PathVariable Integer id) {
		Receita receita = receitaService.findById(id);
		return ResponseEntity.ok().body(receita);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Receita> atualizar(@PathVariable Integer id, @RequestBody Receita receita) {
			Receita atualizaReceita = receitaService.update(id, receita);
			if(atualizaReceita != null) {
				return ResponseEntity.ok().body(atualizaReceita);
			}	
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Receita> delete(@PathVariable Integer id){
			receitaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
