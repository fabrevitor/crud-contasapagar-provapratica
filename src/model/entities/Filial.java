package model.entities;

import java.io.Serializable;

public class Filial implements Serializable {

	private static final long serialVersionUcodigo = 1L;

	private Integer codigo;
	private String nome;
	
	public Filial() {
	}

	public Filial(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Integer getcodigo() {
		return codigo;
	}

	public void setcodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Filial other = (Filial) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Filial [codigo=" + codigo + ", nome=" + nome + "]";
	}
}
