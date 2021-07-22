package com.web.demo.controller;
/**
 * @author An Nguyen
 */
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.web.demo.config.WebUtilsAn;
import com.web.demo.entity.Category;
import com.web.demo.entity.Discount;
import com.web.demo.entity.Games;
import com.web.demo.entity.ImageData;
import com.web.demo.service.AdminBillServiceAn;
import com.web.demo.service.AdminGameServiceAn;
import com.web.demo.service.CategoryService;
import com.web.demo.service.DiscountServiceAn;
import com.web.demo.service.ImageServiceAn;
@Controller
public class GameControllerAn {
	
	@Autowired
	AdminGameServiceAn gameService;
	
	@Autowired
	CategoryService cate;
	
	@Autowired
	AdminBillServiceAn billService;
	
	@Autowired
	DiscountServiceAn discount;
	
	@Autowired
	ImageServiceAn imageService;
	
	@GetMapping("admin/addgame")
	public String addgame(Model model, Principal principal) {
		
		model.addAttribute("game", new Games());
		
		model.addAttribute("discounts", new Discount());
		
		List<Category> listcate = cate.findAll();
		model.addAttribute("listcate", listcate);
		//get discount
		List<Discount> disct = discount.findAll();
		model.addAttribute("discount", disct);
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtilsAn.toStringManager(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "admin/newgame";
	}
	@GetMapping("editgame/{id}")
	public String editgame(@PathVariable(name="id") Integer id, Model model, Principal principal) {
		model.addAttribute("discounts", new Discount());
		//get game
		Games game = gameService.getById(id);	
		model.addAttribute("game", game);
		//get category
		List<Category> listcate = cate.findAll();
		model.addAttribute("listcate", listcate);
		//get discount
		List<Discount> disct = discount.findAll();
		model.addAttribute("discount", disct);
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtilsAn.toStringManager(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "admin/newgame";
	}
	@RequestMapping(value = "/savegame", method = RequestMethod.POST)
	public String savegame(@ModelAttribute("game") Games game, @RequestParam("files[]") MultipartFile[] images,
			 Model model ) {
		Games g = gameService.save(game);
		try {
			Arrays.asList(images).stream().forEach(image -> {
				imageService.store(image);
				String file=StringUtils.cleanPath(image.getOriginalFilename());
				ImageData imagedata=new ImageData();
				imagedata.setGames(g);
				imagedata.setNameImage(file);
				imageService.save(imagedata);	
			});
		}catch(Exception e) {
			System.out.println("Empty file name !!!");
		}
		return "redirect:/admin/listgame";
	}
	@GetMapping("admin/listgame")
	public String listgame(Model model, Principal principal) {
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtilsAn.toStringManager(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "admin/Listofgame";
	}
	@GetMapping("/delgameimage/{id}")
	public String delete(@PathVariable Integer id){
			 ImageData img = imageService.getById(id);
			 Games g = img.getGames();
			 int idg = g.getIdGame();
			 imageService.deleteById(id);
			 return "redirect:/editgame/"+idg;
	}
}
