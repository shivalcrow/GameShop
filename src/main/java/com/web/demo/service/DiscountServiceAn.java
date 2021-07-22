package com.web.demo.service;
/**
 * @author AnNguyen
 */
import java.util.List;

import com.web.demo.entity.Discount;

public interface DiscountServiceAn {

	List<Discount> findAll();

	<S extends Discount> S save(S entity);

	Discount getById(Integer id);

}
