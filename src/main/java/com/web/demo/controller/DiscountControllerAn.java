package com.web.demo.controller;
/**
 * @author An
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.demo.entity.Category;
import com.web.demo.entity.Discount;
import com.web.demo.entity.Games;
import com.web.demo.service.CategoryService;
import com.web.demo.service.DiscountServiceAn;

@Controller
public class DiscountControllerAn {
	
	@Autowired
	CategoryService cate;
	
	@Autowired
	DiscountServiceAn discounts;
	
	@GetMapping("editdiscount/{id}")
	public String editgame(@PathVariable(name="id") Integer id, Model model) {
		model.addAttribute("game", new Games());
		//get category
		List<Category> listcate = cate.findAll();
		model.addAttribute("listcate", listcate);
		//get discount
		List<Discount> disct = discounts.findAll();
		model.addAttribute("discount", disct);
		//get discount
		Discount dc = discounts.getById(id);	
		model.addAttribute("discounts", dc);
		return "admin/newgame";
	}
	@RequestMapping(value = "/savediscount", method = RequestMethod.POST)
	public String savegame(@ModelAttribute("discount") Discount discount) {
		discounts.save(discount);
		return "redirect:/admin/addgame";
	}
}
