package com.web.demo.controller;
/**
 * show all games
 * 
 * @author đạt hà
 * @param model
 * @return index
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.demo.entity.Category;
import com.web.demo.service.CategoryService;
@Controller
public class CategoryController {
	@Autowired
	CategoryService cateservice;

	@GetMapping("/category")
	public String getcategory(Model model){
		List<Category> listcate= cateservice.findAll();
		model.addAttribute("listcate",listcate);
		return "index";
	}
}
