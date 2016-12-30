package com.cairone.appexample.dtos;

import java.math.BigDecimal;

public class PrestamoFrmDto {

	private BigDecimal prestamo = null;
	private String tipoTasa = null;
	private BigDecimal tasa = null;
	private Integer cuotas = null;
	private BigDecimal alicuota = null;
	
	public PrestamoFrmDto() {}

	public BigDecimal getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(BigDecimal prestamo) {
		this.prestamo = prestamo;
	}

	public String getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(String tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	public BigDecimal getTasa() {
		return tasa;
	}

	public void setTasa(BigDecimal tasa) {
		this.tasa = tasa;
	}

	public Integer getCuotas() {
		return cuotas;
	}

	public void setCuotas(Integer cuotas) {
		this.cuotas = cuotas;
	}

	public BigDecimal getAlicuota() {
		return alicuota;
	}

	public void setAlicuota(BigDecimal alicuota) {
		this.alicuota = alicuota;
	}
	
}
