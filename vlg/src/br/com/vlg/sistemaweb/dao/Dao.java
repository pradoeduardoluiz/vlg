package br.com.vlg.sistemaweb.dao;

import javax.persistence.EntityManager;

import br.com.vlg.sistemaweb.util.JpaUtil;

public abstract class Dao {

	EntityManager getEntityManager() {
		EntityManager entityManager = JpaUtil.getEntityManager();
		return entityManager;
	}

}
