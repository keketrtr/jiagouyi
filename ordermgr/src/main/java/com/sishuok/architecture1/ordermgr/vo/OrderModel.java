package com.sishuok.architecture1.ordermgr.vo;

import com.sishuok.architecture1.common.vo.BaseModel;

public class OrderModel extends BaseModel<OrderModel> {
	private String orderTime;
	private Float totalMoney;
	private Float saveMoney;
	private Short state;

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Float getSaveMoney() {
		return saveMoney;
	}

	public void setSaveMoney(Float saveMoney) {
		this.saveMoney = saveMoney;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

}
