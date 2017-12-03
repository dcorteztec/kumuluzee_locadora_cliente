package br.com.marteleto.framework.persistence.business.interfaces;

import java.io.Serializable;
import java.util.List;

import br.com.marteleto.framework.persistence.exception.BusinessException;
import br.com.marteleto.framework.persistence.vo.interfaces.IPersistenceEntity;

public interface IBusiness<T extends IPersistenceEntity> extends Serializable {
	T getEntityById(Long id);
	List<T> listEntities();
	void save(T object) throws BusinessException;
	void delete(T object) throws BusinessException;
    void saveInTransaction(T object) throws BusinessException;
    void deleteInTransaction(T object) throws BusinessException;
    void beginTransaction() throws BusinessException;
    void rollbackTransaction() throws BusinessException;
    void commitTransaction() throws BusinessException; 
}