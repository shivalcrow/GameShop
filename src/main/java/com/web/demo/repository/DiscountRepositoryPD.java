package com.web.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.demo.entity.Discount;

/*
 * 
 * @author PhatDat
 */
@Repository
public interface DiscountRepositoryPD extends JpaRepository<Discount,Integer> {
	
}
