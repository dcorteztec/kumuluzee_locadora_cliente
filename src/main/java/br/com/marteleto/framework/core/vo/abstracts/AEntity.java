package br.com.marteleto.framework.core.vo.abstracts;

import br.com.marteleto.framework.core.vo.interfaces.IEntity;

public abstract class AEntity implements IEntity {
	private static final long serialVersionUID = 1L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
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
		AEntity other = (AEntity) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		if (this.getId() != null) {
			return "[ " + this.getClass().getSimpleName() + ": " + this.getId() + " ]";
		}
		return super.toString();
	}
}