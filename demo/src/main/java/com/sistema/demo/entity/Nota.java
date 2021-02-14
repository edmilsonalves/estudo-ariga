package com.sistema.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
