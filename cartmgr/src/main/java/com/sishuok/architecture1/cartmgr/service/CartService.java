package com.sishuok.architecture1.cartmgr.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sishuok.architecture1.cartmgr.dao.CartDAO;
import com.sishuok.architecture1.cartmgr.vo.CartModel;
import com.sishuok.architecture1.cartmgr.vo.CartQueryModel;
import com.sishuok.architecture1.common.service.BaseService;
@Service
public class CartService extends BaseService<CartModel, CartQueryModel>
	implements ICartService{
	private CartDAO dao;

	@Resource
	public void setDao(CartDAO dao) {
		this.dao = dao;
		super.setDao(dao);
	}
	
}
