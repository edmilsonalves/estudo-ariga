package com.sistema.demo.dto;

import java.util.List;

public class AlunoDto {

	private int id;
	private String nome;
	private List<NotaDto> listNota;
	private Double media;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<NotaDto> getListNota() {
		return listNota;
	}

	public void setListNota(List<NotaDto> listNota) {
		this.listNota = listNota;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

}
