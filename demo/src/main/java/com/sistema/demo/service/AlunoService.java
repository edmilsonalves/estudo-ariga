package com.sistema.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.demo.dto.AlunoDto;
import com.sistema.demo.dto.NotaDto;
import com.sistema.demo.entity.Aluno;
import com.sistema.demo.entity.Nota;
import com.sistema.demo.repository.AlunoRepository;

@Service
public class AlunoService {

	private final Logger logger = LoggerFactory.getLogger(AlunoService.class);

	@Autowired
	private AlunoRepository alunoRepository;

	public AlunoDto findByNome(String nome) {
		Aluno aluno = this.alunoRepository.findByNome(nome);

		AlunoDto dto = new AlunoDto();
		dto.setId(aluno.getId());
		dto.setNome(aluno.getNome());

		return dto;
	}

	public List<AlunoDto> findAll() {
		List<Aluno> listAluno = this.alunoRepository.findAll();

		if (listAluno != null && !listAluno.isEmpty()) {
			List<AlunoDto> list = new ArrayList<>(0);
			for (Aluno aluno : listAluno) {
				AlunoDto dto = new AlunoDto();
				dto.setId(aluno.getId());
				dto.setNome(aluno.getNome());

				for (Nota nota : aluno.getListNota()) {
					NotaDto notaDto = new NotaDto();
					notaDto.setSemestre(nota.getSemestre());
					notaDto.setNota(nota.getNota());
					if (dto.getListNota() == null) {
						dto.setListNota(new ArrayList<>(0));
					}
					dto.getListNota().add(notaDto);
				}

				list.add(dto);
			}

			return list;
		}

		return null;
	}
}
