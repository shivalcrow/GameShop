package com.web.demo.controller;
/**
 * @author NguyenHuuSon
 */

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.web.demo.config.WebUtils;
import com.web.demo.entity.Blog;
import com.web.demo.entity.Category;
import com.web.demo.entity.CommentBlog;
import com.web.demo.entity.ReplyCommentBlog;
import com.web.demo.entity.Users;
import com.web.demo.service.BlogService;
import com.web.demo.service.CategoryService;
import com.web.demo.service.CommentBlogService;
import com.web.demo.service.ReplyCommentBlogService;
import com.web.demo.service.UserServiceImpSon;

@Controller
public class BlogController {
	@Autowired
	CommentBlogService commentblog;
	@Autowired
	ReplyCommentBlogService reply;
	@Autowired
	UserServiceImpSon userService;
	@Autowired
	BlogService blogservice;
	@Autowired
	CategoryService cateservice;

	@GetMapping("blog")
	public String blog(Model model, String message, HttpSession session, Principal principal) {
		model.addAttribute("user", new Users());
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

		List<Blog> listblog = blogservice.findAll();
		model.addAttribute("listblog", listblog);
		List<Category> listcate = cateservice.findAll();
		model.addAttribute("listcate", listcate);
		return "shop/blog-1";
	}

	@GetMapping("blogdetail/{id}")
	public String det(Model model, String message, HttpSession session, Principal principal,
			@PathVariable("id") int id) {
		model.addAttribute("user", new Users());
		model.addAttribute("commentblog", new CommentBlog());
		model.addAttribute("replycommentblog", new ReplyCommentBlog());
		// model.addAttribute("user", new Users());

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
		
		Optional<Blog> bl = blogservice.findById(id);
		if (bl.isPresent()) {
			model.addAttribute("blogdt", bl.get());
			List<Category> listcate = cateservice.findAll();
			model.addAttribute("listcate", listcate);
			return "shop/blog-detail-1";
		} else {
			return "shop/blog-1";
		}

	}

	@PostMapping("/commentblog/add")
	public String savecomment(@ModelAttribute("commentblog") CommentBlog cmb, HttpServletRequest req,
			HttpSession session,@RequestParam(name="idblog") int idblog ,Model model) {
		String fe = req.getHeader("REFERER");
		if (session.getAttribute("userinfoname") == null) {
			
			return "redirect:" + fe + "?message=loginreq";
		} else {
			Users u = userService.findByusernameUsers(session.getAttribute("userinfoname").toString());
			Optional<Blog> b=blogservice.findById(idblog);
			if (u != null) {
				cmb.setUsers(u);
				cmb.setDate(new Date());
				cmb.setBlog(b.get());
				commentblog.save(cmb);
			}
			return "redirect:" + fe;
		}
	}
	@PostMapping("repcomment/add")
	public String rep(@ModelAttribute("replycommentblog") ReplyCommentBlog cmb, HttpServletRequest req,
			HttpSession session,@RequestParam(name="idcmt") int idcmt ,Model model) {
		String fe = req.getHeader("REFERER");
		if (session.getAttribute("userinfoname") == null) {
			
			return "redirect:" + fe + "?message=loginreq";
		}else {
			Users u = userService.findByusernameUsers(session.getAttribute("userinfoname").toString());
			Optional<CommentBlog> b=commentblog.findById(idcmt);
			LocalDateTime ldt = LocalDateTime.now();
			Date date2 = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
			if (u != null) {
				cmb.setUser(u);
				cmb.setDate(date2);
				cmb.setCommentBlog(b.get());
				reply.save(cmb);
			}
			return "redirect:" + fe;
		}
	}
}
