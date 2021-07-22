package com.web.demo.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.web.demo.config.WebUtilsAn;
import com.web.demo.entity.Blog;
import com.web.demo.entity.Users;
import com.web.demo.service.AdminUserServiceAn;
import com.web.demo.service.BlogService;
import com.web.demo.service.ImageServiceAn;

/**
 * 
 * @author AnNguyen
 *
 */
@Controller
public class AdminBlogControllerAn {
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	AdminUserServiceAn userService;
	
	@Autowired
	ImageServiceAn imgService;
	
	@GetMapping("admin/listblog")
	public String listblog(Model model, Principal principal) {
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtilsAn.toStringManager(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		List<Blog> blogs = blogService.findAll();
		model.addAttribute("blogs", blogs);
		return "admin/listblog";
	}
	@GetMapping("admin/addblog")
	public String addblog(Model model, Principal principal) {
		String userInfo = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = WebUtilsAn.toStringManager(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		Users user = userService.findByusernameUsers(userInfo);
		model.addAttribute("user", user);
		Blog blog = new Blog();
		Date date = new Date();
		blog.setDateBlog(date);
		model.addAttribute("blog", blog);
		return "admin/addblog";
	}
	@GetMapping("/editblog/{id}")
	public String editBlog(Model model, Principal principal, @PathVariable Integer id) {
		String userInfo = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = WebUtilsAn.toStringManager(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		Users user = userService.findByusernameUsers(userInfo);
		model.addAttribute("user", user);
		Optional<Blog> blog = blogService.findById(id);
		Blog bl = blog.get();
		model.addAttribute("blog", bl);
		return "admin/addblog";
	}
	@PostMapping("/saveblog")
	public String saveblog(@ModelAttribute("blog") Blog blog, @RequestParam("file") MultipartFile file, Principal principal) {
		if(!file.getOriginalFilename().isEmpty()) {
			imgService.store(file);
			blog.setImageBlog(file.getOriginalFilename());
		}else {
			
		}
		String userInfo = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = WebUtilsAn.toStringManager(loginedUser);
		}
		Users user = userService.findByusernameUsers(userInfo);
		blog.setUsers(user);
		if(blog.getIdBlog()==null) {
			Date date = new Date();
			blog.setDateBlog(date);
		}
		if(blog.getViewBlog() == null) {
			blog.setViewBlog(0);
		}
		blogService.save(blog);
		return "redirect:/admin/listblog";
	}
	@GetMapping("/deleteblog/{id}")
	public String deleteBlog(@PathVariable Integer id) {
		Optional<Blog> blog = blogService.findById(id);
		Blog bl = blog.get();
		blogService.delete(bl);
		return "redirect:/admin/listblog";
	}
}
