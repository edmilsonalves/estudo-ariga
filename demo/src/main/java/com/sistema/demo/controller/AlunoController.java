package com.sistema.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.demo.dto.AlunoDto;
import com.sistema.demo.service.AlunoService;

@RestController
@RequestMapping(value = "/app/v1/sistema-online/aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@GetMapping
	public ResponseEntity<?> findAll() {

		List<AlunoDto> listAluno = this.alunoService.findAll();
		if (listAluno == null || listAluno.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listAluno);

	}

}
