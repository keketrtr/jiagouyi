package com.sishuok.architecture1.common.service;

import com.sishuok.pageutil.Page;

public interface IBaseService<M,QM> {
	public void create(M m);

	public void update(M m);

	public void delete(int uuid);

	public M getByUuid(int uuid);

	public Page<M> getByConditionPage(QM qm);
}