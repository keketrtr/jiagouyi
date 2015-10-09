package com.sishuok.architecture1.common.service;


import java.util.List;

import com.sishuok.architecture1.common.dao.BaseDAO;
import com.sishuok.architecture1.common.vo.BaseModel;
import com.sishuok.pageutil.Page;

public class BaseService<M, QM extends BaseModel<M>> implements IBaseService<M, QM> {
	private BaseDAO<M, QM> dao;
	
	public void setDao(BaseDAO<M, QM> dao) {
		this.dao = dao;
	}

	public void create(M m) {
		dao.create(m);
	}

	public void update(M m) {
		dao.update(m);
	}

	public void delete(int uuid) {
		dao.delete(uuid);
	}

	public M getByUuid(int uuid) {
		return dao.getByUuid(uuid);
	}

	public Page<M> getByConditionPage(QM qm) {
		List<M> list = dao.getByConditionPage(qm);
		qm.getPage().setResult(list);
		return qm.getPage();
	}


}
