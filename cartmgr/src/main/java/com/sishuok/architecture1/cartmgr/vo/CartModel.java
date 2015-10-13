package com.sishuok.architecture1.cartmgr.vo;

import com.sishuok.architecture1.common.vo.BaseModel;

public class CartModel extends BaseModel<CartModel> {
	private Integer customerUuid;
	private Integer goodUuid;
	private Integer buyNum;

	public Integer getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(Integer customerUuid) {
		this.customerUuid = customerUuid;
	}

	public Integer getGoodUuid() {
		return goodUuid;
	}

	public void setGoodUuid(Integer goodUuid) {
		this.goodUuid = goodUuid;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

}
