package com.web.demo.controller;
import java.net.URI;
import java.net.URISyntaxException;
/**
 * @author Phat Dat
 */
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.demo.config.WebUtils;
import com.web.demo.entity.Category;
import com.web.demo.entity.CommentGame;
import com.web.demo.entity.Games;
import com.web.demo.entity.ImageData;
import com.web.demo.entity.ReplyCommentGame;
import com.web.demo.entity.Users;
import com.web.demo.repository.ReplyCommentGameRepoPD;
import com.web.demo.service.CategoryService;
import com.web.demo.service.DiscountServicePD;
import com.web.demo.service.GameImageServiceSon;
import com.web.demo.service.GamesServicePD;
import com.web.demo.service.ImageDataServicePD;
import com.web.demo.service.ReplyCommentGameServicePD;
import com.web.demo.service.UserCommentGameServicePD;
import com.web.demo.service.UserServiceSon;

/**
 * @author PhatDat get list of games and search display detailed info of a game
 */
@Controller
public class ShopControllerPhatDat {
	@Autowired
	private DiscountServicePD discountService;

	@Autowired
	private ImageDataServicePD imageGameService;

	@Autowired
	private GamesServicePD gameService;

	@Autowired
	CategoryService cateService;

	@Autowired
	private UserCommentGameServicePD commentService;
	
	@Autowired
	private ReplyCommentGameServicePD replyCommentService;

	@Autowired
	CategoryService cateservice;
	@Autowired
	GameImageServiceSon idataservice;

	@Autowired
	UserServiceSon userService;
	
	private String addPath = "";
	


	/*
	 * @GetMapping(value = "/shoptest/{pageNo}") public String
	 * shop1(@PathVariable(value = "pageNo") int pageNo, Model model) {
	 * //model.addAttribute("img", imageGameService.getImageGame(1));
	 * //model.addAttribute("game", gameService.getGame(1));
	 * //model.addAttribute("discount", discountService.getDiscount(1));
	 * //System.out.println(imageGameService.getImageGame(1)); List<Category>
	 * listcate= cateService.findAll(); model.addAttribute("listcate",listcate); int
	 * pageSize = 4;
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
	 */

	@RequestMapping(value = "/shop/detailgame")
	public String gameDetail1(Model model, @RequestParam("id") Integer idGame,Principal principal,
			@ModelAttribute("comment") CommentGame comment,
			@ModelAttribute("reply") ReplyCommentGame reply,
			HttpServletRequest request,HttpSession session,
			Users user, @RequestParam(required = false) String message,
			@RequestParam(value = "idCmt", required = false) Integer idCmt) {
		// String username = comment.getUsers().getUsernameUsers();
		
		// Regis
				model.addAttribute("user", user);
				//model.addAttribute("user", new Users());

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
		// Integer idGame = Integer.parseInt(params.get("id"));
		
		String url = request.getRequestURL().toString() + "?id=" + idGame.toString();
		model.addAttribute("URL", url);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users user1 = userService.findByusernameUsers(username);
		String cmt = comment.getContentCommentGame();
		String rep = reply.getContentComment();
		// String cmt = params.get("cmt");
		if (user1 != null) {
			
			model.addAttribute("avatar", user1.getImageUsers());
			model.addAttribute("usernameUsers", username);
			if (cmt == null) {
				comment = new CommentGame();
				model.addAttribute("comment", comment);
			} else {
				commentService.addCommentGame(idGame, user1, cmt);
			}
			Integer idcmt = idCmt;
			if(idcmt != null) {
				if (rep == null) {
					reply = new ReplyCommentGame();
					model.addAttribute("reply", reply);
				} else {
					replyCommentService.addReplyCommentGame(idcmt, user1, rep);
				}
			}

		} else {
			model.addAttribute("avatar", "defaultavatar.png");
			model.addAttribute("usernameUsers", "guest");
		}
		
		//model.addAttribute("contentCommentGame", "");
		comment.setContentCommentGame("");
		model.addAttribute("game", gameService.getGame(idGame));
		model.addAttribute("images", imageGameService.getImageDetailGame(idGame));
		model.addAttribute("id", idGame);
		
		model.addAttribute("user", new Users());

		// add comment game
		model.addAttribute("cmts", commentService.getCommentGame(idGame));
		model.addAttribute("recGames", gameService.getRelatedGames(idGame));
		model.addAttribute("recImgGames", imageGameService.getRelatedImageList(idGame));
		model.addAttribute("reps", replyCommentService.getReplyCommentGame(idGame));
		System.out.println("-------------------\n"+replyCommentService.getReplyCommentGame(idGame).toString());
		/**
		 * @author Dat Ha
		 */
		List<Category> listcate = cateservice.findAll();
		model.addAttribute("listcate", listcate);
		return "shop/gameinfo";
	}

	/*
	 * @PostMapping(value = "/shop/detailgame") public String saveCommentGame(Model
	 * model, // @RequestParam(value = "users.usernameUsers", required = false)
	 * String name, // @RequestParam(value = "contentCommentGame", required = false)
	 * String cmt,
	 * 
	 * @ModelAttribute("comment") CommentGame comment) { Integer idUser, idGame;
	 * idGame = (Integer)model.getAttribute("idGame");
	 * 
	 * if(idGame == null) { idGame = 3; }
	 * 
	 * idUser = comment.getUsers().getIdUsers();
	 * commentService.addCommentGame(idGame, idUser,
	 * comment.getContentCommentGame());
	 * 
	 * 
	 * 
	 * model.addAttribute("game", gameService.getGame(idGame));
	 * model.addAttribute("images", imageGameService.getImageDetailGame(idGame));
	 * //save comment game idUser =
	 * userService.findByusernameUsers(name).getIdUsers();
	 * commentService.addCommentGame(idGame, idUser, cmt);
	 * 
	 * //add comment game model.addAttribute("cmts",
	 * commentService.getCommentGame(idGame)); model.addAttribute("recGames",
	 * gameService.getRelatedGames(idGame)); model.addAttribute("recImgGames",
	 * imageGameService.getRelatedImageList(idGame));
	 * 
	 * 
	 * return "redirect:/shop/detailgame"; }
	 */

	@GetMapping(value = { "/shop/{pageNo}", "/shop" })
	public String shop1(Model model, 
			@PathVariable(value = "pageNo", required = false) Integer pageNo,
			@Param("keyword") String keyword, Principal principal, 
			@RequestParam(required = false) String message,
			@RequestParam(value = "size", defaultValue = "4") Integer pageSize,
			Users user, HttpSession session, HttpServletRequest request) {

		// Regis
		model.addAttribute("user", user);
		//model.addAttribute("user", new Users());

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
//		System.out.println(message);
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
		
		
		addPath = "..";
		model.addAttribute("addPath", addPath);
		//int pageSize = 4;
		if (pageNo == null) {
			pageNo = 1;
			
		} 
		else if (pageNo.intValue() == 0) {
			pageNo = 1;
			
		}
		
		
		String url = request.getHeader("REFERER");
		if(url == null) {
			url = request.getRequestURL().toString();
		}

		
		System.out.println("--------------------------\n"
				+ "URL : "+ url);
		
		if(!url.matches(".*\\d.*")){
			url += url + "/1";
		}
//		String urlWithoutQuery = "";
		
//		if(url.contains("?")) {
//			String[] arrString = url.split("\\?");
//			urlWithoutQuery = arrString[0];
//			queryString =arrString[1];
//			System.out.println("--------------------------");
//			System.out.println(urlWithoutQuery+" - " + queryString +"\n--------------------------");
//			String urlPage = urlWithoutQuery.substring(urlWithoutQuery.lastIndexOf('/'));
//			if(urlPage.matches(".*\\d.*")) {
//				int i = urlWithoutQuery.lastIndexOf('/');
//				urlPage = urlWithoutQuery.substring(0, i);
//				urlWithoutQuery = urlPage;
//			}
//	
//
//		}
//		else {
//			urlWithoutQuery = url;
//		}
		
//		model.addAttribute("URLPage", urlWithoutQuery);
//		model.addAttribute("URLQuery", queryString);
//		System.out.println("\n\n--------------------------");
//		System.out.println("URLQuery = " +queryString.split("\\=")[0] + "\n--------------------------");
		
		
		if(url.contains("?")) {
			int index = url.indexOf('?');
			String url1 = url.substring(0, index);
			int index1 = url1.lastIndexOf('/');
			String url2 = url.substring(index1, index);
			if(url2.matches(".*\\d.*")) {
				String url3 = url.substring(0, index1) + "/1?"+url.substring(index);
				url = url3;
			}else { 
				String url4 = url.substring(0, index) + "/1?"+url.substring(index);
				url = url4;
			}
			
		}else {
			int index2 = url.lastIndexOf('/');
			String url5 = url.substring(index2);
			if(url5.matches(".*\\d.*")) {
				String url6 = url.substring(0, index2) + "/1";
				url = url6;
			}else { 
				String url4 = url + "/1";
				url = url4;
			}
			
		}
	
		String urlPage = url.substring(url.lastIndexOf('/'));
		if(urlPage.matches(".*\\d.*")) {
			int i = url.lastIndexOf('/');
			urlPage = url.substring(0, i);
		}
		model.addAttribute("URLPage", urlPage);
		
		

		Page<Games> page = null;
		int count = 0;
		if (keyword != null && !keyword.isEmpty()) {
			page = gameService.listAllGamesPaginated(keyword, pageNo, pageSize);
			count = gameService.countSearchGames(keyword);
			//url=request.getHeader("REFERER");
			
		} else {
			page = gameService.findAllPaginated(pageNo, pageSize);
		}

		List<Games> listAllGames = page.getContent();
		

		model.addAttribute("URL", url);
		model.addAttribute("keyword", keyword);
		model.addAttribute("size", pageSize);
		model.addAttribute("images1", imageGameService.getImageList());
		model.addAttribute("listAllGames", listAllGames);
		model.addAttribute("countSearch", count);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		System.out.println("\n---------------\ntotalPages = "+page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		System.out.println(imageGameService.getImageList());
		System.out.println(request.getHeader("REFERER"));
		System.out.println("keyword: " + keyword + " - size: "+ pageSize);

		/**
		 * @author Dat Ha
		 */
		List<Category> listcate = cateservice.findAll();
		model.addAttribute("listcate", listcate);

		return "shop/shop1";
	}

	@GetMapping(value = { "/shop/games/{term}/{pageNo}" })
	public String shop2(Model model, @PathVariable(value = "pageNo") Integer pageNo,
			@PathVariable(value = "term", required = false) String term, 
			@RequestParam(value = "size", defaultValue = "4") int pageSize,
			@RequestParam(required = false) String message,
			HttpServletRequest request, Principal principal,
			Users user, HttpSession session) {
		// pageSize = 5;
		
		// Regis
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
//		System.out.println(message);
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

		Page<Games> page = gameService.findAllPaginated(pageNo, pageSize);
		List<Games> listAllGames = page.getContent();
		model.addAttribute("term", term);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("images1", imageGameService.getImageList());
		model.addAttribute("user", new Users());
		// model.addAttribute("listGames", listEmployees);
		
		if (pageNo == null) {
			pageNo = 1;
		} 
		else if (pageNo.intValue() == 0) {
			pageNo = 1;
		}
		model.addAttribute("pageNo", pageNo);
		String url = request.getRequestURL().toString();
		

		if(!url.matches(".*\\d.*")){
			url += url + "/1";
		}
		
		
		int index2 = url.lastIndexOf('/');
		String url5 = url.substring(index2);
		if(url5.matches(".*\\d.*")) {
			String url6 = url.substring(0, index2) + "/1";
			url = url6;
		}else { 
			String url4 = url + "/1";
			url = url4;
		}
	
		String urlPage = url.substring(url.lastIndexOf('/'));
		if(urlPage.matches(".*\\d.*")) {
			int i = url.lastIndexOf('/');
			urlPage = url.substring(0, i);
		}
		
		
		model.addAttribute("URL", url);
		model.addAttribute("URLPage", urlPage);
		model.addAttribute("size", pageSize);

		model.addAttribute("images1", imageGameService.getImageList());
		if (term == null || term.isEmpty()) {
			model.addAttribute("listAllGames", listAllGames);
		} else if (term.equalsIgnoreCase("bestSelling")) {
			page = gameService.findGamesByFilterPaginated(pageNo, pageSize, "Count_sell");
			model.addAttribute("listAllGames", page.getContent());
			// model.addAttribute("games1", gameService.getGamesByFilter("Count_sell",
			// Integer.MAX_VALUE));
		} else if (term.equalsIgnoreCase("recommended")) {
			page = gameService.findGamesByFilterPaginated(pageNo, pageSize, "Rate_game");
			model.addAttribute("listAllGames", page.getContent());
			// model.addAttribute("games1", gameService.getGamesByFilter("Rate_game",
			// Integer.MAX_VALUE));
		} else if (term.equalsIgnoreCase("newRelease")) {
			page = gameService.findGamesByFilterPaginated(pageNo, pageSize, "ReleaseYear_game");
			model.addAttribute("listAllGames", page.getContent());
			// model.addAttribute("games1", gameService.getGamesByFilter("ReleaseYear_game",
			// Integer.MAX_VALUE));
		}
		else if (term.equalsIgnoreCase("offers")) {
			page = gameService.findGamesByFilterPaginated(pageNo, pageSize, "discount");
			model.addAttribute("listAllGames", page.getContent());
			// model.addAttribute("games1", gameService.getGamesByFilter("ReleaseYear_game",
			// Integer.MAX_VALUE));
		}
		System.out.println(request.getHeader("REFERER"));

		/**
		 * @author Dat Ha
		 */
		List<Category> listcate = cateservice.findAll();
		model.addAttribute("listcate", listcate);

		return "shop/shop1";
	}

	@GetMapping(value = "/shop/categories/{cate}/{pageNo}")
	public String shopCategory(Model model, @PathVariable(value = "pageNo") Integer pageNo,
			Principal principal, HttpSession session,
			@PathVariable(value = "cate") int idCate,
			@RequestParam(value = "size", defaultValue = "12", required = false) int pageSize,
			@RequestParam(required = false) String message,
			HttpServletRequest request, Users user) {

			

		// model.addAttribute("img", imageGameService.getImageGame(1));
		// model.addAttribute("game", gameService.getGame(1));
		// model.addAttribute("discount", discountService.getDiscount(1));
		// System.out.println(imageGameService.getImageGame(1));

		// Regis
		model.addAttribute("user", user);
		
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

		Page<Games> page = gameService.findGamesByCategoryPaginated(pageNo, pageSize, idCate);

		List<Games> listAllGames = page.getContent();
		
		model.addAttribute("size", pageSize);
		model.addAttribute("cate", idCate);
		model.addAttribute("images1", imageGameService.getImageList());
		model.addAttribute("listAllGames", listAllGames);
		model.addAttribute("countSearch", listAllGames.size());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("user", new Users());
		
		
		if (pageNo == null) {
			pageNo = 1;
		} 
		else if (pageNo.intValue() == 0) {
			pageNo = 1;
		}
		model.addAttribute("pageNo", pageNo);
		
		String url = request.getRequestURL().toString();
		
		if(!url.matches(".*\\d.*")){
			url += url + "/1";
		}
		
		
		int index2 = url.lastIndexOf('/');
		String url5 = url.substring(index2);
		if(url5.matches(".*\\d.*")) {
			String url6 = url.substring(0, index2) + "/1";
			url = url6;
		}else { 
			String url4 = url + "/1";
			url = url4;
		}
	
		String urlPage = url.substring(url.lastIndexOf('/'));
		if(urlPage.matches(".*\\d.*")) {
			int i = url.lastIndexOf('/');
			urlPage = url.substring(0, i);
		}
		
		
		model.addAttribute("URL", url);
		model.addAttribute("URLPage", urlPage);

		/**
		 * @author Dat Ha
		 */
		List<Category> listcate = cateservice.findAll();
		model.addAttribute("listcate", listcate);

		return "shop/shop1";
	}

}
