package com.cairone.appexample.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class PrestamoCuotaDto implements Comparable<PrestamoCuotaDto>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer numero = null;
	private BigDecimal capital = null;
	private BigDecimal interes = null;
	private BigDecimal iva = null;
	private BigDecimal interesesGravados = null;
	private BigDecimal monto = null;
	private BigDecimal saldoCapital = null;
	
	public PrestamoCuotaDto() {}

	public PrestamoCuotaDto(Integer numero, BigDecimal capital,
			BigDecimal interes, BigDecimal iva, BigDecimal interesesGravados,
			BigDecimal monto, BigDecimal saldoCapital) {
		super();
		this.numero = numero;
		this.capital = capital;
		this.interes = interes;
		this.iva = iva;
		this.interesesGravados = interesesGravados;
		this.monto = monto;
		this.saldoCapital = saldoCapital;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getInteres() {
		return interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getInteresesGravados() {
		return interesesGravados;
	}

	public void setInteresesGravados(BigDecimal interesesGravados) {
		this.interesesGravados = interesesGravados;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public BigDecimal getSaldoCapital() {
		return saldoCapital;
	}

	public void setSaldoCapital(BigDecimal saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrestamoCuotaDto other = (PrestamoCuotaDto) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public int compareTo(PrestamoCuotaDto o) {
		return this.numero.compareTo(o.getNumero());
	}
}
