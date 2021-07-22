package com.web.demo.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.demo.entity.Category;
import com.web.demo.entity.Games;
import com.web.demo.service.CategoryService;
import com.web.demo.service.DiscountServicePD;
import com.web.demo.service.GamesServicePD;
import com.web.demo.service.ImageDataServicePD;
import com.web.demo.service.UserCommentGameServicePD;

/**
 * @author PhatDat
 * get list of games and search
 * display detailed info of a game
 */
@Controller
public class ShopControllerPhatDat1 {
	/*
	 * @Autowired private DiscountServicePD discountService;
	 * 
	 * @Autowired private ImageDataServicePD imageGameService;
	 * 
	 * 
	 * @Autowired private GamesServicePD gameService;
	 * 
	 * @Autowired CategoryService cateService;
	 * 
	 * @Autowired private UserCommentGameServicePD comment;
	 * 
	 * @GetMapping(value = "/shoptest/{pageNo}") public String
	 * shop1(@PathVariable(value = "pageNo") int pageNo, Model model) {
	 * //model.addAttribute("img", imageGameService.getImageGame(1));
	 * //model.addAttribute("game", gameService.getGame(1));
	 * //model.addAttribute("discount", discountService.getDiscount(1));
	 * //System.out.println(imageGameService.getImageGame(1)); List<Category>
	 * listcate= cateService.findAll(); model.addAttribute("listcate",listcate); int
	 * pageSize = 4;
	 * 
	 * 
	 * Page<Games> page = gameService.findAllPaginated(pageNo, pageSize);
	 * List<Games> listAllGames = page.getContent();
	 * model.addAttribute("currentPage", pageNo); model.addAttribute("totalPages",
	 * page.getTotalPages()); model.addAttribute("totalItems",
	 * page.getTotalElements()); //model.addAttribute("listGames", listEmployees);
	 * 
	 * model.addAttribute("images1", imageGameService.getImageList());
	 * model.addAttribute("listAllGames", listAllGames);
	 * 
	 * return "shop/shoptest"; }
	 * 
	 * @RequestMapping(value = "/shoptest/{pageNo}") public String shopSearch(Model
	 * model, @Param("keyword") String keyword, @PathVariable(value = "pageNo") int
	 * pageNo) { int pageSize = 4; Page<Games> page;
	 * 
	 * if(keyword != null && !keyword.isEmpty()) { page =
	 * gameService.listAllGamesPaginated(keyword, pageNo, pageSize); } else { page =
	 * gameService.findAllPaginated(pageNo, pageSize); } List<Games> listAllGames =
	 * page.getContent(); model.addAttribute("listAllGames", listAllGames);
	 * model.addAttribute("keyword", keyword); model.addAttribute("currentPage",
	 * pageNo); model.addAttribute("totalPages", page.getTotalPages());
	 * model.addAttribute("totalItems", page.getTotalElements());
	 * model.addAttribute("images1", imageGameService.getImageList());
	 * 
	 * return "shop/shoptest"; }
	 * 
	 * @RequestMapping(value = "/shop/game") public String gameDetail(Model
	 * model, @RequestParam("id") int id) { List<Category> listcate=
	 * cateService.findAll(); model.addAttribute("listcate",listcate);
	 * model.addAttribute("game", gameService.getGame(id));
	 * model.addAttribute("images", imageGameService.getImageDetailGame(id)); return
	 * "shop/gamedetails"; }
	 * 
	 * 
	 * @GetMapping(value = "/shop1/detailgame") public String gameDetail1(Model
	 * model, @RequestParam("id") int id) { model.addAttribute("game",
	 * gameService.getGame(id)); model.addAttribute("images",
	 * imageGameService.getImageDetailGame(id)); //add comment game
	 * model.addAttribute("cmts", comment.getCommentGame(id));
	 * model.addAttribute("recGames", gameService.getRelatedGames(id));
	 * model.addAttribute("recImgGames", imageGameService.getRelatedImageList(id));
	 * return "shop/gameinfo"; }
	 * 
	 * @GetMapping(value = "/shop1/{pageNo}") public String shop1(Model
	 * model, @PathVariable(value = "pageNo") int pageNo) {
	 * //model.addAttribute("img", imageGameService.getImageGame(1));
	 * //model.addAttribute("game", gameService.getGame(1));
	 * //model.addAttribute("discount", discountService.getDiscount(1));
	 * //System.out.println(imageGameService.getImageGame(1)); int pageSize = 3;
	 * Page<Games> page = gameService.findAllPaginatedSorted(pageNo, pageSize);
	 * 
	 * List<Games> listAllGames = page.getContent();
	 * 
	 * model.addAttribute("images1", imageGameService.getImageList());
	 * model.addAttribute("listAllGames", listAllGames);
	 * model.addAttribute("currentPage", pageNo); model.addAttribute("totalPages",
	 * page.getTotalPages()); model.addAttribute("totalItems",
	 * page.getTotalElements());
	 * 
	 * return "shop/shop1"; }
	 * 
	 */
	
}
