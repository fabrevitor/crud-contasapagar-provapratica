package model.entities;

import java.io.Serializable;

public class AttributeLocal implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Double saldoTotal;

	public AttributeLocal() {
	}
	
	public AttributeLocal(Double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public Double getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(Double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	@Override
	public String toString() {
		return "AttributeLocal [saldoTotal=" + saldoTotal + "]";
	}

}
