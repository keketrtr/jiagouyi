package com.sishuok.architecture1.ordermgr.vo;

import com.sishuok.architecture1.common.vo.BaseModel;

public class OrderDetailModel extends BaseModel<OrderDetailModel> {
	private Integer orderUuid;
	private Integer goodsUuid;
	private Integer orderNum;
	private Float price;// 单价
	private Float money;// 总金额
	private Float saveMoney;// 节省金额

	public Integer getOrderUuid() {
		return orderUuid;
	}

	public void setOrderUuid(Integer orderUuid) {
		this.orderUuid = orderUuid;
	}

	public Integer getGoodsUuid() {
		return goodsUuid;
	}

	public void setGoodsUuid(Integer goodsUuid) {
		this.goodsUuid = goodsUuid;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Float getSaveMoney() {
		return saveMoney;
	}

	public void setSaveMoney(Float saveMoney) {
		this.saveMoney = saveMoney;
	}

}
