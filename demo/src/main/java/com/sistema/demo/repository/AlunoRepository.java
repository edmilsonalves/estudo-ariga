package com.sistema.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.demo.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	public Aluno findByNome(String descricao);

}
