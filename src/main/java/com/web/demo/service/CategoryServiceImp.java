package com.web.demo.service;
/**
 * category
 * 
 * @author đạt hà
 * 
 * 
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.demo.entity.Category;
import com.web.demo.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	CategoryRepository cate;

	@Override
	public List<Category> findAll() {
		return cate.findAll();
	}
	
}
