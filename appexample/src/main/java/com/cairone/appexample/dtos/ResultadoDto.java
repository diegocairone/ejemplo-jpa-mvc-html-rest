package com.cairone.appexample.dtos;

public class ResultadoDto {
	
	private Long key = null;
	private Iterable<PrestamoCuotaDto> prestamoCuotaDtos = null;
	
	public ResultadoDto(Long key, Iterable<PrestamoCuotaDto> prestamoCuotaDtos) {
		super();
		this.key = key;
		this.prestamoCuotaDtos = prestamoCuotaDtos;
	}

	public Long getKey() {
		return key;
	}

	public Iterable<PrestamoCuotaDto> getPrestamoCuotaDtos() {
		return prestamoCuotaDtos;
	}
	
}
