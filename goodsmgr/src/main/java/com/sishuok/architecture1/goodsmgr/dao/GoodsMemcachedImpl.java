package com.sishuok.architecture1.goodsmgr.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sishuok.architecture1.common.dao.BaseDAOMemcachedImpl;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.pageutil.Page;

@Repository
public class GoodsMemcachedImpl extends BaseDAOMemcachedImpl<GoodsModel, GoodsQueryModel> implements GoodsDAO {
	
	private GoodsMapperDAO mapper;

	@Resource
	public void setMapper(GoodsMapperDAO mapper) {
		this.mapper = mapper;
		super.setMapper(mapper);
	}

	public static void main(String[] args) {
		GoodsMemcachedImpl impl = new GoodsMemcachedImpl();
		System.out.println(impl.getEntityClass().getSimpleName());
	}

	@Override
	public Page<GoodsModel> getByCondition(GoodsQueryModel qm) {
		return null;
	}

}
