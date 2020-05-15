package br.com.ecotarifas.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.ecotarifas.model.data.AbstractEntity;

@Entity
@Table(name="Bandeiras", uniqueConstraints={@UniqueConstraint(columnNames={"taxaFixa"})})
@SequenceGenerator(name="sequence", sequenceName="default_sequence", allocationSize=5)
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bandeiras extends AbstractEntity {
	//GERADOR-INI Geração de código automático, NÃO ALTERE até o final da geração automática :variáveis
	public static final int CLAS_GERAL = 0;
	public static final int CLAS_USUARIO = 1;
	public static final int TIPO_ALFANUMERICO = 0;
	public static final int TIPO_INTEIRO = 1;
	public static final int TIPO_DATA = 2;
	public static final int TIPO_HORA = 3;
	public static final int TIPO_DECIMAL = 4;

	private Long bandeirasid;

	@NotNull(message="bandVerde é obrigatório!")
	private java.math.BigDecimal bandVerde;
	
	@NotNull(message="bandAmarela é obrigatório!")
	private java.math.BigDecimal bandAmarela;
	
	@NotNull(message="bandVermelha1 é obrigatório!")
	private java.math.BigDecimal bandVermelha1;
	
	@NotNull(message="bandVermelha2 é obrigatório!")
	private java.math.BigDecimal bandVermelha2;
	
	@NotNull(message="taxaFixa é obrigatório!")
	private java.math.BigDecimal taxaFixa;
	
	@NotNull(message="verde é obrigatrio!")
	private Integer verde;
	
	@NotNull(message="amarela é obrigatrio!")
	private Integer amarela;
	
	@NotNull(message="vermelha1 é obrigatrio!")
	private Integer vermelha1;
	
	@NotNull(message="vermelha2 é obrigatrio!")
	private Integer vermelha2;
	
	//GERADOR-FIM Fim da geração de código automático

	//GERADOR-INI Geração de código automático, NÃO ALTERE até o final da geração automática :métodos
	public Bandeiras() {}

	public Bandeiras(Long id) {
		this();
		this.setId(id);
	}

	@Id
	@GeneratedValue(generator="sequence")
	@Column(name="bandeirasid", nullable=false)
	public Long getBandeirasid() {
		return bandeirasid;
	}
	public void setBandeirasid(Long bandeirasid) {
		this.bandeirasid = bandeirasid;
	}
	@javax.persistence.Transient
	@Override
	public Long getId() {
		return getBandeirasid();
	}
	@Override
	public void setId(Long id) {
		this.bandeirasid = id;
	}
	@Column(name="bandverde", nullable=false, precision=12, scale=6)
	public java.math.BigDecimal getBandVerde() {
		return bandVerde;
	}
	public void setBandVerde(java.math.BigDecimal bandVerde) {
		this.bandVerde = bandVerde;
	}
	@Column(name="bandamarela", nullable=false, precision=12, scale=6)
	public java.math.BigDecimal getBandAmarela() {
		return bandAmarela;
	}
	public void setBandAmarela(java.math.BigDecimal bandAmarela) {
		this.bandAmarela = bandAmarela;
	}
	@Column(name="bandvermelha1", nullable=false, precision=12, scale=6)
	public java.math.BigDecimal getBandVermelha1() {
		return bandVermelha1;
	}
	public void setBandVermelha1(java.math.BigDecimal bandVermelha1) {
		this.bandVermelha1 = bandVermelha1;
	}
	@Column(name="bandvermelha2", nullable=false, precision=12, scale=6)
	public java.math.BigDecimal getBandVermelha2() {
		return bandVermelha2;
	}
	public void setBandVermelha2(java.math.BigDecimal bandVermelha2) {
		this.bandVermelha2 = bandVermelha2;
	}
	@Column(name="taxafixa", nullable=false, precision=12, scale=6)
	public java.math.BigDecimal getTaxaFixa() {
		return taxaFixa;
	}
	public void setTaxaFixa(java.math.BigDecimal taxaFixa) {
		this.taxaFixa = taxaFixa;
	}
	@Column(name="verde", nullable=false, precision=12, scale=6)
	public Integer getVerde() {
		return verde;
	}
	public void setVerde(Integer verde) {
		this.verde = verde;
	}
	@Column(name="amarela", nullable=false, precision=12, scale=6)
	public Integer getAmarela() {
		return amarela;
	}
	public void setAmarela(Integer amarela) {
		this.amarela = amarela;
	}
	@Column(name="vermelha1", nullable=false, precision=12, scale=6)
	public Integer getVermelha1() {
		return vermelha1;
	}
	public void setVermelha1(Integer vermelha1) {
		this.vermelha1 = vermelha1;
	}
	@Column(name="vermelha2", nullable=false, precision=12, scale=6)
	public Integer getvermelha2() {
		return vermelha2;
	}
	public void setVermelha2(Integer vermelha2) {
		this.vermelha2 = vermelha2;
	}
	
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taxaFixa == null) ? 0 : taxaFixa.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if(this==obj) return true;
		if(null == obj) return false;
		if(getClass() != obj.getClass()) return false;
		final Consumo other = (Consumo) obj;
		if(this.taxaFixa == null) {
			if(other.getData() != null) return false;
		}else if(!this.taxaFixa.equals(other.getData())) return false;
		return true;
	}
	//GERADOR-FIM Fim da geração de código automático
}