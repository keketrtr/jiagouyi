package com.sishuok.architecture1.storemgr.vo;

import com.sishuok.architecture1.common.vo.BaseModel;

public class StoreModel extends BaseModel<StoreModel> {
	private Integer goodsUuid;
	private Integer storeNum;

	public Integer getGoodsUuid() {
		return goodsUuid;
	}

	public void setGoodsUuid(Integer goodsUuid) {
		this.goodsUuid = goodsUuid;
	}

	public Integer getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(Integer storeNum) {
		this.storeNum = storeNum;
	}

}
