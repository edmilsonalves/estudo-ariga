package com.sistema.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "so_nota")
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int semestre;

	private Double nota;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_aluno", referencedColumnName = "id")
	private Aluno aluno;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
