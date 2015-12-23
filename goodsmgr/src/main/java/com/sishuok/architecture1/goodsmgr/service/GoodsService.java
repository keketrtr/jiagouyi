package com.sishuok.architecture1.goodsmgr.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sishuok.architecture1.common.service.BaseService;
import com.sishuok.architecture1.goodsmgr.dao.GoodsDAO;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.pageutil.Page;
@Service
public class GoodsService extends BaseService<GoodsModel, GoodsQueryModel>
	implements IGoodsService{
	private GoodsDAO dao;

	@Resource
	public void setDao(GoodsDAO dao) {
		this.dao = dao;
		super.setDao(dao);
	}
	@Override
	public Page<GoodsModel> getByConditionPage(GoodsQueryModel qm){
		return dao.getByCondition(qm);
	}
	
}
