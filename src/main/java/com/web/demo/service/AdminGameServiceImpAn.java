 package com.web.demo.service;
/**
 * @author An Nguyen
 */
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.demo.entity.Category;
import com.web.demo.entity.Discount;
import com.web.demo.entity.Games;
import com.web.demo.repository.AdminGameRepoAn;

@Service
public class AdminGameServiceImpAn implements AdminGameServiceAn {

	@Override
	public Optional<Games> findById(Integer id) {
		return game.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		game.deleteById(id);
	}

	AdminGameRepoAn game;

	public AdminGameServiceImpAn(AdminGameRepoAn game) {
		super();
		this.game = game;
	}

	@Override
	public List<Games> findAll() {
		return game.findAll();
	}

	@Override
	public <S extends Games> Games save(S entity) {
		Discount d = entity.getDiscount();
		int value = d.getValue();
		long price =  entity.getPrice();
		long price_fix = price - price*value/100;
		entity.setPriceFix(price_fix);
		if(entity.getRateGame() == null) {
			entity.setRateGame((float) 0);
		}
		if(entity.getCountSell() == null) {
			entity.setCountSell((int) 0);
		}
		if(entity.getCountRate() == null) {
			entity.setCountRate((int) 0);
		}
		return game.save(entity);
	}
	
	@Override
	public Games getById(Integer id) {
		return game.getById(id);
	}

	@Override
	public List<Games> findAllTop() {
		return game.findAllTop();
	}

	@Override
	public List<Games> findBycategories(Category cate) {
		return game.findBycategories(cate);
	}
	
}
