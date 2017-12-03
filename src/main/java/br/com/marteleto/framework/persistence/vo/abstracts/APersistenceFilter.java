package br.com.marteleto.framework.persistence.vo.abstracts;

import br.com.marteleto.framework.core.vo.abstracts.AEntity;
import br.com.marteleto.framework.persistence.vo.interfaces.IPersistenceFilter;

public abstract class APersistenceFilter extends AEntity implements IPersistenceFilter {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer pageSize;
	private Integer first;
	private String sortField;
	private String sortOrder;
	private String text;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getFirst() {
		return first;
	}
	public void setFirst(Integer first) {
		this.first = first;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
