package com.sistema.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.demo.dto.AlunoDto;
import com.sistema.demo.dto.NotaDto;
import com.sistema.demo.entity.Aluno;
import com.sistema.demo.entity.Nota;
import com.sistema.demo.exception.BusinessException;
import com.sistema.demo.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public List<AlunoDto> listByNome(String nome) {

		List<Aluno> listAluno = this.alunoRepository.listByNome(nome);

		List<AlunoDto> listAlunoResponse = convertToDtoList(listAluno);

		return listAlunoResponse;
	}

	public List<AlunoDto> findAll() {

		List<Aluno> listAluno = this.alunoRepository.findAll();

		List<AlunoDto> listAlunoResponse = convertToDtoList(listAluno);

		return listAlunoResponse;
	}

	public List<AlunoDto> listByAprovado() {
		return null;
	}

	public void salvar(final AlunoDto alunoDto) throws BusinessException {

		if (alunoDto.getNome() == null) {
			throw new BusinessException("O campo nome é obrigatório");
		}

		Aluno aluno = new Aluno();
		aluno.setNome(alunoDto.getNome());
		aluno.setListNota(new ArrayList<>(0));
		
		for (NotaDto notaDto : alunoDto.getListNota()) {
			Nota nota = new Nota();
			nota.setAluno(aluno);
			nota.setNota(notaDto.getNota());
			nota.setSemestre(notaDto.getSemestre());
			
			
			aluno.getListNota().add(nota);
			
		}

		this.alunoRepository.save(aluno);
	}

	private List<AlunoDto> convertToDtoList(List<Aluno> listAluno) {

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
