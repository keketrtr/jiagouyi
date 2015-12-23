package com.sishuok.architecture1.goodsmgr.dao;

import java.io.Serializable;
import java.util.List;

import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;

public interface GoodsMongoDao extends GoodsDAO {
	public List<Serializable> getIdsByConditionPage(GoodsQueryModel qm);
	
	public List<GoodsModel> getByIds(List<Serializable> ids);
}
