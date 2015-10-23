package com.sishuok.architecture1.storemgr.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sishuok.architecture1.common.service.BaseService;
import com.sishuok.architecture1.storemgr.dao.StoreDAO;
import com.sishuok.architecture1.storemgr.vo.StoreModel;
import com.sishuok.architecture1.storemgr.vo.StoreQueryModel;
@Service
public class StoreService extends BaseService<StoreModel, StoreQueryModel>
	implements IStoreService{
	private StoreDAO dao;

	@Resource
	public void setDao(StoreDAO dao) {
		this.dao = dao;
		super.setDao(dao);
	}

	@Override
	public StoreModel getByGoodsUuid(int goodsUuid) {
		return dao.getByGoodsUuid(goodsUuid);
	}
	
}
