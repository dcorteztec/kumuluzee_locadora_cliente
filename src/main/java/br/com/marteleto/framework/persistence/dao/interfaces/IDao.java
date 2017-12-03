package br.com.marteleto.framework.persistence.dao.interfaces;

import java.io.Serializable;

import javax.persistence.EntityManager;

public interface IDao extends Serializable {
	EntityManager getEntityManager();
}