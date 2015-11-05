package com.sishuok.architecture1.common.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<M,QM> {
	public void create(M m);

	public void update(M m);

	public void delete(int uuid);

	public M getByUuid(int uuid);

	public List<M> getByConditionPage(QM qm);
	
	public List<Serializable> getIdsByConditionPage(QM qm);
	
	public List<M> getByIds(List<Serializable> ids);
	
}
