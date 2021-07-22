package com.web.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.demo.entity.Category;
import com.web.demo.entity.Games;
import com.web.demo.service.AdminGameServiceAn;

/**
 * 
 * @author havud
 *
 */
@Controller
public class GamebyCategororyController {
 @Autowired
 AdminGameServiceAn gameservice;
	
	@GetMapping("/category/{id}")
	public String gamecategory(Model model,@PathVariable int id) {
		Category cate=new Category();
		cate.setIdCategory(id);
		List<Games> gamebyCate=gameservice.findBycategories(cate);
		model.addAttribute("listgamebycate", gamebyCate);
		return "index";
	}
}
