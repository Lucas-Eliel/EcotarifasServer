package br.com.ecotarifas.model.data;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = -7335158215941941428L;

	public abstract Long getId();
	public abstract void setId(Long id);
	
	public boolean isNew() {
		return getId() == null;
	}
}