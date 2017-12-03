package br.com.marteleto.framework.persistence.business.abstracts;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

import br.com.marteleto.framework.persistence.business.interfaces.IBusiness;
import br.com.marteleto.framework.persistence.dao.interfaces.IDao;
import br.com.marteleto.framework.persistence.exception.BusinessException;
import br.com.marteleto.framework.persistence.vo.interfaces.IPersistenceEntity;

@SuppressWarnings({"unchecked"})
public abstract class ABusiness<T extends IPersistenceEntity> implements IBusiness<T> {
	private static final long serialVersionUID = 1L;
	protected Logger LOG = Logger.getLogger(this.getClass().getName());
	private Class<T> persistentClass;
	//@Resource private UserTransaction userTransaction;
	
	protected abstract IDao getDao();
	
	public ABusiness() {  
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];  
    }
	
	protected UserTransaction getTransaction() {
		//this.getDao().getEntityManager().getTransaction();
		UserTransaction userTransaction = null;
		try {
			userTransaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
		} catch (NamingException e) {
		}
		return userTransaction;
	}
	
	@Override
	public T getEntityById(Long id) {
        return this.getDao().getEntityManager().find(this.persistentClass, id);
    }
	
	@Override
	public List<T> listEntities() {
		String name = this.persistentClass.getSimpleName() + ".listEntities";
        return this.getDao().getEntityManager().createNamedQuery(name, this.persistentClass).getResultList();
    }
	
	@Override
	public void save(T object) throws BusinessException {
		try {
			this.beginTransaction();
			this.saveInTransaction(object);
			this.commitTransaction();
		} catch (Exception ex) {
			this.rollbackTransaction();
			throw new BusinessException(ex);
		}
	}
	
	@Override
	public void saveInTransaction(T object) throws BusinessException {
		try {
			if (object.getId() == null) {
				this.getDao().getEntityManager().persist(object);
			} else {
				this.getDao().getEntityManager().merge(object);
			}
			this.getDao().getEntityManager().flush();
			this.getDao().getEntityManager().clear();
		} catch (Exception ex) {
			throw new BusinessException(ex);
		}
	}
	
	@Override
	public void delete(T object) throws BusinessException {
		try {
			this.beginTransaction();
			this.deleteInTransaction(object);
			this.commitTransaction();
		} catch (Exception ex) {
			this.rollbackTransaction();
			throw new BusinessException(ex);
		}
	}
	
	@Override
	public void deleteInTransaction(T object) throws BusinessException {
		try {
			//this.getDao().getEntityManager().remove(object);
			this.getDao().getEntityManager().remove(this.getDao().getEntityManager().getReference(object.getClass(), object.getId()));
			this.getDao().getEntityManager().flush();
			this.getDao().getEntityManager().clear();
		} catch (Exception ex) {
			throw new BusinessException(ex);
		}
	}

	@Override
	public void beginTransaction() throws BusinessException {
		try {
			if (this.getTransaction() != null) {
				this.getTransaction().begin();
			}
		} catch (Exception ex) {
			throw new BusinessException(ex);
		}
	}
	
	@Override
	public void rollbackTransaction() throws BusinessException {
		try {
			if (this.getTransaction() != null) {
				this.getTransaction().rollback();
			}
		} catch (Exception ex) {
			throw new BusinessException(ex);
		}
	}
	
	@Override
	public void commitTransaction() throws BusinessException {
		try {
			if (this.getTransaction() != null) {
				this.getTransaction().commit();
			}
		} catch (Exception ex) {
			throw new BusinessException(ex);
		}
	}
}