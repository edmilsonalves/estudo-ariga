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
		
		List<Aluno> listAluno = this.alunoRepository.findAll();

		List<AlunoDto> listAlunoResponse = new ArrayList<>(0);

		for (Aluno aluno : listAluno) {
			if (aluno.getMedia() >= 7.0) {
				AlunoDto alunoDto = new AlunoDto();
				alunoDto.setId(aluno.getId());
				alunoDto.setNome(aluno.getNome());
				alunoDto.setMedia(aluno.getMedia());
				
				alunoDto.setListNota(new ArrayList<>(0));
				for (Nota nota : aluno.getListNota()) {
					NotaDto notaDto = new NotaDto(); 
					notaDto.setNota(nota.getNota());
					notaDto.setSemestre(nota.getSemestre());
					
					alunoDto.getListNota().add(notaDto);
				}
				
				listAlunoResponse.add(alunoDto);
			}
		}
		
		return listAlunoResponse;
	}
	
	public List<AlunoDto> listByReprovado() {
		
		List<Aluno> listAluno = this.alunoRepository.findAll();

		List<AlunoDto> listAlunoResponse = new ArrayList<>(0);

		for (Aluno aluno : listAluno) {
			if (aluno.getMedia() < 7.0) {
				AlunoDto alunoDto = new AlunoDto();
				alunoDto.setId(aluno.getId());
				alunoDto.setNome(aluno.getNome());
				alunoDto.setMedia(aluno.getMedia());
				
				alunoDto.setListNota(new ArrayList<>(0));
				for (Nota nota : aluno.getListNota()) {
					NotaDto notaDto = new NotaDto(); 
					notaDto.setNota(nota.getNota());
					notaDto.setSemestre(nota.getSemestre());
					
					alunoDto.getListNota().add(notaDto);
				}
				
				listAlunoResponse.add(alunoDto);
			}
		}
		
		return listAlunoResponse;
	}

	public void salvar(final AlunoDto alunoDto) throws BusinessException {

		if (alunoDto.getNome() == null) {
			throw new BusinessException("O campo nome é obrigatório");
		}

		Aluno aluno = new Aluno();
		aluno.setNome(alunoDto.getNome());
		aluno.setListNota(new ArrayList<>(0));

		Double soma = 0D;
		for (NotaDto notaDto : alunoDto.getListNota()) {
			Nota nota = new Nota();
			nota.setAluno(aluno);
			nota.setNota(notaDto.getNota());
			nota.setSemestre(notaDto.getSemestre());

			aluno.getListNota().add(nota);
			soma = soma + notaDto.getNota();
		}
		
		aluno.setMedia(soma / alunoDto.getListNota().size());

		this.alunoRepository.save(aluno);
	}

	private List<AlunoDto> convertToDtoList(List<Aluno> listAluno) {

		if (listAluno != null && !listAluno.isEmpty()) {
			List<AlunoDto> list = new ArrayList<>(0);
			for (Aluno aluno : listAluno) {
				AlunoDto alunoDto = new AlunoDto();
				alunoDto.setId(aluno.getId());
				alunoDto.setNome(aluno.getNome());
				alunoDto.setMedia(aluno.getMedia());

				for (Nota nota : aluno.getListNota()) {
					NotaDto notaDto = new NotaDto();
					notaDto.setSemestre(nota.getSemestre());
					notaDto.setNota(nota.getNota());

					if (alunoDto.getListNota() == null) {
						alunoDto.setListNota(new ArrayList<>(0));
					}

					alunoDto.getListNota().add(notaDto);
				}

				list.add(alunoDto);
			}

			return list;
		}
		return null;
	}
}
