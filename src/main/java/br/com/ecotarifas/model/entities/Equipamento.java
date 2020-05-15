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
@Table(name="Equipamento", uniqueConstraints={@UniqueConstraint(columnNames={"nome"})})
@SequenceGenerator(name="sequence", sequenceName="default_sequence", allocationSize=5)
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Equipamento extends AbstractEntity {
	//GERADOR-INI Geração de código automático, NÃO ALTERE até o final da geração automática :variáveis
	public static final int CLAS_GERAL = 0;
	public static final int CLAS_USUARIO = 1;
	public static final int TIPO_ALFANUMERICO = 0;
	public static final int TIPO_INTEIRO = 1;
	public static final int TIPO_DATA = 2;
	public static final int TIPO_HORA = 3;
	public static final int TIPO_DECIMAL = 4;

	private Long equipamentoid;

	@NotNull(message="Nome é obrigatrio!")
	private String nome;
	
	private java.math.BigDecimal meta;
	
	private java.math.BigDecimal ctrldiario;

	private Integer arduino;
	//GERADOR-FIM Fim da geração de código automático

	//GERADOR-INI Geração de código automático, NÃO ALTERE até o final da geração automática :métodos
	public Equipamento() {}

	public Equipamento(Long id) {
		this();
		this.setId(id);
	}

	@Id
	@GeneratedValue(generator="sequence")
	@Column(name="equipamentoid", nullable=false)
	public Long getEquipamentoid() {
		return equipamentoid;
	}
	public void setEquipamentoid(Long equipamentoid) {
		this.equipamentoid = equipamentoid;
	}
	@javax.persistence.Transient
	@Override
	public Long getId() {
		return getEquipamentoid();
	}
	@Override
	public void setId(Long id) {
		this.equipamentoid = id;
	}
	@Column(name="nome", nullable=false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column(name="meta", nullable=false, precision=12, scale=6)
	public java.math.BigDecimal getMeta() {
		return meta;
	}
	public void setMeta(java.math.BigDecimal meta) {
		this.meta = meta;
	}
	@Column(name="ctrldiario", nullable=false, precision=12, scale=6)
	public java.math.BigDecimal getCtrldiario() {
		return ctrldiario;
	}
	public void setCtrldiario(java.math.BigDecimal ctrldiario) {
		this.ctrldiario = ctrldiario;
	}
	@Column(name="arduino", nullable=false, precision=12, scale=6)
	public Integer getArduino() {
		return arduino;
	}
	public void setArduino(Integer arduino) {
		this.arduino = arduino;
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