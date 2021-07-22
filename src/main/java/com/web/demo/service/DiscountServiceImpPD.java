package com.web.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.demo.entity.Discount;

import com.web.demo.repository.DiscountRepositoryPD;
/**
 * @author PhatDat
 *
 */

@Service
public class DiscountServiceImpPD implements DiscountServicePD{
	@Autowired
	DiscountRepositoryPD discountRepository;
	
	/*
	 * get a game's discount
	 * @author PhatDat
	 */
	@Override
	public Discount getDiscount(int i) {
		return discountRepository.getById(i);
	}
	
	/*
	 * get list of games' discount values
	 * @author PhatDat
	 */
	@Override
	public List<Discount> getDiscountList() {
		return discountRepository.findAll();
	}
	
}
