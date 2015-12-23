package com.sishuok.architecture1.goodsmgr.dao;

import javax.annotation.Resource;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.sishuok.architecture1.common.dao.BaseDAOMemcachedImpl;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.pageutil.Page;

@Repository
@Primary
public class GoodsMongoImpl extends BaseDAOMemcachedImpl<GoodsModel, GoodsQueryModel> implements GoodsDAO {
	
	private GoodsMongoDao mapper;

	@Resource
	public void setMapper(GoodsMongoDao mapper) {
		this.mapper = mapper;
		super.setMapper(mapper);
	}

	public static void main(String[] args) {
		GoodsMongoImpl impl = new GoodsMongoImpl();
		System.out.println(impl.getEntityClass().getSimpleName());
	}

	@Override
	public Page<GoodsModel> getByCondition(GoodsQueryModel qm) {
		return mapper.getByCondition(qm);
	}

}
