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
@Table(name="Controle", uniqueConstraints={@UniqueConstraint(columnNames={"nome"})})
@SequenceGenerator(name="sequence", sequenceName="default_sequence", allocationSize=5)
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Controle extends AbstractEntity {
	//GERADOR-INI Geração de código automático, NÃO ALTERE até o final da geração automática :variáveis
	public static final int CLAS_GERAL = 0;
	public static final int CLAS_USUARIO = 1;
	public static final int TIPO_ALFANUMERICO = 0;
	public static final int TIPO_INTEIRO = 1;
	public static final int TIPO_DATA = 2;
	public static final int TIPO_HORA = 3;
	public static final int TIPO_DECIMAL = 4;

	private Long controleid;

	@NotNull(message="Nome é obrigatrio!")
	private String nome;
	
	@NotNull(message="Status é obrigatrio!")
	private Integer status;
	
	@NotNull(message="Data Status é obrigatório!")
	private LocalDate statdtini;
	
	@NotNull(message="Controle é obrigatrio!")
	private Integer controle;
	
	@NotNull(message="Data Controle é obrigatório!")
	private LocalDate ctrldtini;
	
	@NotNull(message="Estado é obrigatrio!")
	private Integer estado;
	//GERADOR-FIM Fim da geração de código automático

	//GERADOR-INI Geração de código automático, NÃO ALTERE até o final da geração automática :métodos
	public Controle() {}

	public Controle(Long id) {
		this();
		this.setId(id);
	}

	@Id
	@GeneratedValue(generator="sequence")
	@Column(name="controleid", nullable=false)
	public Long getControleid() {
		return controleid;
	}
	public void setControleid(Long controleid) {
		this.controleid = controleid;
	}
	@javax.persistence.Transient
	@Override
	public Long getId() {
		return getControleid();
	}
	@Override
	public void setId(Long id) {
		this.controleid = id;
	}
	@Column(name="nome", nullable=false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column(name="status", nullable=false, precision=12, scale=6)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="statdtini", nullable=false)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public LocalDate getStatdtini() {
		return statdtini;
	}
	public void setStatdtini(LocalDate statdtini) {
		this.statdtini = statdtini;
	}
	@Column(name="controle", nullable=false, precision=12, scale=6)
	public Integer getControle() {
		return controle;
	}
	public void setControle(Integer controle) {
		this.controle = controle;
	}
	@Column(name="ctrldtini", nullable=false)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public LocalDate getCtrldtini() {
		return ctrldtini;
	}
	public void setCtrldtini(LocalDate ctrldtini) {
		this.ctrldtini = ctrldtini;
	}
	@Column(name="estado", nullable=false, precision=12, scale=6)
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if(this==obj) return true;
		if(null == obj) return false;
		if(getClass() != obj.getClass()) return false;
		final Equipamento other = (Equipamento) obj;
		if(this.nome == null) {
			if(other.getNome() != null) return false;
		}else if(!this.nome.equals(other.getNome())) return false;
		return true;
	}
	//GERADOR-FIM Fim da geração de código automático
}