package br.com.marteleto.framework.core.vo.interfaces;

import java.io.Serializable;

public interface IEntity extends Serializable {
	public void setId(Long id);
	public Long getId();
}
