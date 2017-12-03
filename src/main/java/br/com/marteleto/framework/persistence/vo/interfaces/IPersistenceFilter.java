package br.com.marteleto.framework.persistence.vo.interfaces;

import br.com.marteleto.framework.core.vo.interfaces.IEntity;

public interface IPersistenceFilter extends IEntity {
	Integer getPageSize();
	void setPageSize(Integer pageSize);
	Integer getFirst();
	void setFirst(Integer first);
	String getSortField();
	void setSortField(String sortField);
	String getSortOrder();
	void setSortOrder(String sortOrder);
	String getText();
	void setText(String text);
}
