package com.sistema.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoDto {

	private int id;
	private String nome;
	private List<NotaDto> listNota;
	private Double media;
}
