package com.web.demo.controller;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.demo.config.WebUtils;
import com.web.demo.entity.Category;
import com.web.demo.entity.Games;
import com.web.demo.entity.SlideShow;
import com.web.demo.entity.Systems;
import com.web.demo.entity.Users;
import com.web.demo.service.CategoryService;
import com.web.demo.service.DiscountServicePD;
import com.web.demo.service.GamesServicePD;
import com.web.demo.service.ImageDataServicePD;
import com.web.demo.service.SlideShowService;
import com.web.demo.service.SystemsService;
import com.web.demo.service.UserServiceSon;

/**
 * @author PhatDat
 *
 */
@Controller
public class HomeController {
	@Autowired
	private DiscountServicePD discountService;
	
	@Autowired
	private ImageDataServicePD imageGameService;
	
	
	@Autowired
	private GamesServicePD gameService;
	
	@Autowired
	UserServiceSon userService;
	@Autowired
	CategoryService cateService;
	
	@Autowired
	SystemsService systemservice;
	
	@Autowired
	SlideShowService slideshowService;
	
	/*
	 * get list of all games and list of games by filters with Pagination
	 * @author PhatDat
	 */
	/*
	 * @GetMapping(value = "/hometest1") public String home1(@RequestParam String
	 * term, @RequestParam(value = "pageNo") int pageNo, Model model) {
	 * //model.addAttribute("img", imageGameService.getImageGame(1));
	 * //model.addAttribute("game", gameService.getGame(1));
	 * //model.addAttribute("discount", discountService.getDiscount(1));
	 * //System.out.println(imageGameService.getImageGame(1));
	 * 
	 * int pageSize = 4;
	 * 
	 * Page<Games> page = gameService.findAllPaginated(pageNo, pageSize);
	 * List<Games> listAllGames = page.getContent(); model.addAttribute("term",
	 * term); model.addAttribute("currentPage", pageNo);
	 * model.addAttribute("totalPages", page.getTotalPages());
	 * model.addAttribute("totalItems", page.getTotalElements());
	 * //model.addAttribute("listGames", listEmployees);
	 * 
	 * model.addAttribute("images1", imageGameService.getImageList()); if( term ==
	 * null || term.isEmpty()){ model.addAttribute("games1", listAllGames); } else
	 * if (term.equalsIgnoreCase("bestSelling")){ page =
	 * gameService.findGamesByFilterPaginated(pageNo, pageSize, "Count_sell");
	 * model.addAttribute("games1", page.getContent());
	 * //model.addAttribute("games1", gameService.getGamesByFilter("Count_sell",
	 * Integer.MAX_VALUE)); } else if (term.equalsIgnoreCase("recommended")){ page =
	 * gameService.findGamesByFilterPaginated(pageNo, pageSize, "Rate_game");
	 * model.addAttribute("games1", page.getContent());
	 * //model.addAttribute("games1", gameService.getGamesByFilter("Rate_game",
	 * Integer.MAX_VALUE)); } else if (term.equalsIgnoreCase("newRelease")){ page =
	 * gameService.findGamesByFilterPaginated(pageNo, pageSize, "ReleaseYear_game");
	 * model.addAttribute("games1", page.getContent());
	 * //model.addAttribute("games1",
	 * gameService.getGamesByFilter("ReleaseYear_game", Integer.MAX_VALUE)); }
	 * 
	 * //model.addAttribute("discounts", discountService.getDiscountList()); return
	 * "hometest1"; }
	 */
	
	/*
	 * @author PhatDat
	 * display home page with games classified by filters
	 */
	@GetMapping(value = "/")
    public String hometest2(Model model) {
		List<Category> listcate= cateService.findAll();
		List<SlideShow> sliders = slideshowService.findAll();
		
		model.addAttribute("slider", sliders);
		LocalDate date=LocalDate.now();
		Systems sys = systemservice.findByDateLike(date.toString());
		if(sys!=null) {
			sys.setViewsSystem(sys.getViewsSystem()+1);
			systemservice.save(sys);
			
		}else {
			Systems system = new Systems();
			system.setDate(new Date());
			system.setDowloadSystem(0);
			system.setViewsSystem(1);
			systemservice.save(system);
			
			//System.out.println(sys.getDate());
		}
		
		
		model.addAttribute("listcate",listcate);
		//model.addAttribute("img", imageGameService.getImageGame(1));
		//model.addAttribute("game", gameService.getGame(1));
		//model.addAttribute("discount", discountService.getDiscount(1));
		//System.out.println(imageGameService.getImageGame(1));
		
		//Top Selling (Count_sell attribute)
		model.addAttribute("images2", imageGameService.getImageList());
		model.addAttribute("games_topSelling", gameService.getGamesByFilter("Count_sell", 4));
		//model.addAttribute("discounts", discountService.getDiscountList());
		//System.out.println(gameService.getGamesByFilter("Count_sell", 2).toString());
		
		//Recommended (Rate_game attribute)
		//model.addAttribute("images_Recommended", imageGameService.getImageList());
		model.addAttribute("games_Recommended", gameService.getGamesByFilter("Rate_game", 1).get(0));
		model.addAttribute("images3", imageGameService.getImageDetailGame(gameService.getGamesByFilter("Rate_game", 1).get(0).getIdGame()));
	
		
		//New Release (Rate_game attribute)
		model.addAttribute("games_New", gameService.getGamesByFilter("ReleaseYear_game", 1).get(0));
		model.addAttribute("images4", imageGameService.getImageDetailGame(gameService.getGamesByFilter("ReleaseYear_game", 1).get(0).getIdGame()).get(0));
		
        return "index";
 
    }
	
	/*
	 * @author PhatDat
	 */
	
//	@GetMapping(value = "/")
//    public String index(Model model) {
//		//model.addAttribute("discount", discountService.getDiscount(1));
//        return "index";
//    }
	
@GetMapping("/userinfo")
public String userinfo(Model model,@RequestParam(required = false) String message,
		HttpServletRequest request, Principal principal,
		Users user, HttpSession session) {
	model.addAttribute("user", user);

	//
	if (message != null && !message.isEmpty()) {
		if (message.equals("logout")) {
			model.addAttribute("message", "Logout!");
			session.removeAttribute("userinfoname");
			session.removeAttribute("userinfoemail");
			session.removeAttribute("userinfoid");
			session.removeAttribute("userinfophone");
		}
		if (message.equals("error")) {
			model.addAttribute("message", "Login Failed!");
			
		}
		if (message.equals("loginreq")) {
			model.addAttribute("message", "Please Login");
		}

	}
//	System.out.println(message);
	if (principal != null) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		Users us = userService.findByusernameUsers(loginedUser.getUsername());
		session.setAttribute("userinfoname", us.getNameUsers());
		session.setAttribute("userinfoemail", us.getEmailUsers());
		session.setAttribute("userinfoid", us.getIdUsers());
		session.setAttribute("userinfophone", us.getPhoneUsers());
		System.out.println(session.getAttribute("userinfoname") + "a" + session.getAttribute("userinfoemail"));
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
	}
	return "shop/maintenance";
	
}
	
}
