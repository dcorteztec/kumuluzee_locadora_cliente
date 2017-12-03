package br.com.marteleto.framework.persistence.dao.abstracts;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.marteleto.framework.persistence.dao.interfaces.IDao;

public abstract class ADao implements IDao {
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}