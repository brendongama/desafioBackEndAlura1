package br.com.desafio.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import br.com.desafio.model.Despesa;
import br.com.desafio.services.DespesaService;

@RestController
@RequestMapping("/despesas")
public class DespesaController {
	
	private static final String ID = "/{id}";
	
	@Autowired
	private DespesaService despesaService;

	@PostMapping
	public ResponseEntity<Despesa> create(@Valid @RequestBody Despesa despesa) {
		Despesa novaDespesa = despesaService.createDespesa(despesa);		
		if(novaDespesa != null ) {
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest().path(ID).buildAndExpand(novaDespesa.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	@GetMapping()
	public ResponseEntity<List<Despesa>> findAll(@RequestParam(required = false) String descricao){
		List<Despesa> despesas;
		if(null == descricao ) {
			despesas = despesaService.findAll();
		}else {
			despesas = despesaService.findByDescricao(descricao);
		}
		
		return ResponseEntity.ok().body(despesas);
	}
	 
	@GetMapping(value = "/{descricao}")
	public ResponseEntity<List<Despesa>> findByDescricao(@RequestParam("descricao") String descricao){
		List<Despesa> despesasDescricao = despesaService.findByDescricao(descricao);
		return ResponseEntity.ok().body(despesasDescricao);		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Despesa> findById(@PathVariable Integer id) {
		Despesa despesa = despesaService.findById(id);
		return ResponseEntity.ok().body(despesa);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Despesa> atualizar(@PathVariable Integer id, @RequestBody Despesa despesa) {
			Despesa atualizaDespesa = despesaService.update(id, despesa);
			if(atualizaDespesa != null) {
				return ResponseEntity.ok().body(atualizaDespesa);
			}	
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Despesa> delete(@PathVariable Integer id){
		despesaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
