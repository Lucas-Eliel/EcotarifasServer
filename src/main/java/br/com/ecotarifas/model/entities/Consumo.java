package br.com.ecotarifas.model.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.ecotarifas.model.data.AbstractEntity;

@Entity
@Table(name="Consumo", uniqueConstraints={@UniqueConstraint(columnNames={"data"})})
@SequenceGenerator(name="sequence", sequenceName="default_sequence", allocationSize=5)
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Consumo extends AbstractEntity {
	//GERADOR-INI Geração de código automático, NÃO ALTERE até o final da geração automática :variáveis
	public static final int CLAS_GERAL = 0;
	public static final int CLAS_USUARIO = 1;
	public static final int TIPO_ALFANUMERICO = 0;
	public static final int TIPO_INTEIRO = 1;
	public static final int TIPO_DATA = 2;
	public static final int TIPO_HORA = 3;
	public static final int TIPO_DECIMAL = 4;

	private Long consumoid;

	@NotNull(message="Nome é obrigatrio!")
	private String nome;
	
	@NotNull(message="Tensao é obrigatrio!")
	private java.math.BigDecimal tensao;
	
	@NotNull(message="Corrente é obrigatrio!")
	private java.math.BigDecimal corrente;
	
	@NotNull(message="Potencia é obrigatrio!")
	private java.math.BigDecimal potencia;
	
	@NotNull(message="Uso é obrigatrio!")
	private java.math.BigDecimal uso;
	
	@NotNull(message="Data é obrigatório!")
	private LocalDate data;
	
	@NotNull(message="Total é obrigatrio!")
	private java.math.BigDecimal total;
	//GERADOR-FIM Fim da geração de código automático

	//GERADOR-INI Geração de código automático, NÃO ALTERE até o final da geração automática :métodos
	public Consumo() {}

	public Consumo(Long id) {
		this();
		this.setId(id);
	}

	@Id
	@GeneratedValue(generator="sequence")
	@Column(name="consumoid", nullable=false)
	public Long getConsumoid() {
		return consumoid;
	}
	public void setConsumoid(Long consumoid) {
		this.consumoid = consumoid;
	}
	@javax.persistence.Transient
	@Override
	public Long getId() {
		return getConsumoid();
	}
	@Override
	public void setId(Long id) {
		this.consumoid = id;
	}
	@Column(name="nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column(name="tensao", nullable=false, precision=12, scale=6)
	public java.math.BigDecimal getTensao() {
		return tensao;
	}
	public void setTensao(java.math.BigDecimal tensao) {
		this.tensao = tensao;
	}
	@Column(name="corrente", nullable=false, precision=12, scale=6)
	public java.math.BigDecimal getCorrente() {
		return corrente;
	}
	public void setCorrente(java.math.BigDecimal corrente) {
		this.corrente = corrente;
	}
	@Column(name="potencia", nullable=false, precision=12, scale=6)
	public java.math.BigDecimal getPotencia() {
		return potencia;
	}
	public void setPotencia(java.math.BigDecimal potencia) {
		this.potencia = potencia;
	}
	@Column(name="uso", nullable=false, precision=12, scale=6)
	public java.math.BigDecimal getUso() {
		return uso;
	}
	public void setUso(java.math.BigDecimal uso) {
		this.uso = uso;
	}
	@Column(name="data", nullable=false)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	@Column(name="total", nullable=false, precision=12, scale=6)
	public java.math.BigDecimal getTotal() {
		return total;
	}
	public void setTotal(java.math.BigDecimal total) {
		this.total = total;
	}
	
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if(this==obj) return true;
		if(null == obj) return false;
		if(getClass() != obj.getClass()) return false;
		final Consumo other = (Consumo) obj;
		if(this.data == null) {
			if(other.getData() != null) return false;
		}else if(!this.data.equals(other.getData())) return false;
		return true;
	}
	//GERADOR-FIM Fim da geração de código automático
}