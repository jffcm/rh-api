package me.joaof.rh.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.joaof.rh.model.Funcionario;
import me.joaof.rh.repository.FuncionarioRepository;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	
	@GetMapping
	public List<Funcionario> listar() {
		return funcionarioRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscar(@PathVariable long id) {
		return funcionarioRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());	
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario cadastrar(@RequestBody Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizar(@PathVariable long id, @RequestBody Funcionario funcionario) {
		
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} 
		
		funcionario.setId(id);
		funcionarioRepository.save(funcionario);
		return ResponseEntity.ok(funcionario);
		
	}
		
}
