package com.web.demo.service;

import java.util.List;

import com.web.demo.entity.Discount;
/*
 * 
 * @author PhatDat
 */
public interface DiscountServicePD {
	public Discount getDiscount(int i);
	
	public List<Discount> getDiscountList();
}
