package com.sistema.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.demo.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	@Query("select aluno from Aluno aluno where aluno.nome LIKE concat('%', :nome , '%')")
	public List<Aluno> listByNome(@Param("nome") String nome);

}
