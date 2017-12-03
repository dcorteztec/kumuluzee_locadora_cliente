package br.com.marteleto.framework.persistence.vo.abstracts;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.marteleto.framework.core.vo.abstracts.AEntity;
import br.com.marteleto.framework.persistence.vo.interfaces.IPersistenceEntity;

@MappedSuperclass
public abstract class APersistenceEntity extends AEntity implements IPersistenceEntity {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQUENCE")
    @Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}