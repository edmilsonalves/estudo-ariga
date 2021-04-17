package com.sistema.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.demo.dto.AlunoDto;
import com.sistema.demo.exception.BusinessException;
import com.sistema.demo.service.AlunoService;

@RestController
@RequestMapping(value = "/app/v1/sistema-online/aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	/**
	 * Consultar todos os alunos
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> findAll() {

		List<AlunoDto> listAluno = this.alunoService.findAll();
		if (listAluno == null || listAluno.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listAluno);

	}

	@GetMapping("/{nome}")
	public ResponseEntity<?> listByNome(@PathVariable final String nome) {

		List<AlunoDto> listAluno = this.alunoService.listByNome(nome);
		if (listAluno == null || listAluno.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listAluno);

	}

	@GetMapping("/aprovado")
	public ResponseEntity<?> listByAprovado() {

		List<AlunoDto> listAluno = this.alunoService.listByAprovado();
		if (listAluno == null || listAluno.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listAluno);

	}
	
	@GetMapping("/reprovado")
	public ResponseEntity<?> listByReprovado() {

		List<AlunoDto> listAluno = this.alunoService.listByReprovado();
		if (listAluno == null || listAluno.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listAluno);

	}


	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody final AlunoDto request) {

		try {

			this.alunoService.salvar(request);

		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

		return ResponseEntity.noContent().build();
	}

}
