package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Contas implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigo;
	private String descricao;
	private Date dataRegistro;
	private Boolean foiPago;
	private Double valor;
	private Date dataPagamento;
	private Double saldoAntes;
	private Double saldoDepois;
	
	private Filial filial;
	private String filNome;
	
	public Contas() {
	}

	
	public Contas(Integer codigo, String descricao, Date dataRegistro, Boolean foiPago, Double valor,
			Date dataPagamento, Double saldoAntes, Double saldoDepois, Filial filial, String filNome) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataRegistro = dataRegistro;
		this.foiPago = foiPago;
		this.valor = valor;
		this.dataPagamento = dataPagamento;
		this.saldoAntes = saldoAntes;
		this.saldoDepois = saldoDepois;
		this.filial = filial;
		this.filNome = filNome;
	}

	public Contas(Integer codigo, String descricao, Date dataRegistro, Boolean foiPago, Double valor, Filial filial, String filNome) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataRegistro = dataRegistro;
		this.foiPago = foiPago;
		this.valor = valor;
		this.filial = filial;
		this.filNome = filNome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public boolean getFoiPago() {
		return foiPago;
	}

	public void setFoiPago(Boolean foiPago) {
		this.foiPago = foiPago;
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
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
		Contas other = (Contas) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contas [codigo=" + codigo + ", descricao=" + descricao + ", dataRegistro=" + dataRegistro + ", foiPago="
				+ foiPago + ", valor=" + valor + ", dataPagamento=" + dataPagamento + ", saldoAntes=" + saldoAntes
				+ ", saldoDepois=" + saldoDepois + ", filial=" + filial + ", filNome=" + filNome + "]";
	}
	
	

	public String getFilNome() {
		return filNome;
	}

	public void setFilNome(String filNome) {
		this.filNome = filNome;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Double getSaldoAntes() {
		return saldoAntes;
	}

	public void setSaldoAntes(Double saldoAntes) {
		this.saldoAntes = saldoAntes;
	}

	public Double getSaldoDepois() {
		return saldoDepois;
	}

	public void setSaldoDepois(Double saldoDepois) {
		this.saldoDepois = saldoDepois;
	}

}
