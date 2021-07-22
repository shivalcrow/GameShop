package com.web.demo.controller;

/**
 * @author NguyenHuuSon
 */
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.web.demo.config.WebUtils;
import com.web.demo.entity.Category;
import com.web.demo.entity.Role;
import com.web.demo.entity.Systems;
import com.web.demo.entity.TokenUser;
import com.web.demo.entity.Users;
import com.web.demo.service.CategoryService;
import com.web.demo.service.SystemsService;
import com.web.demo.service.TokenServiceSon;
import com.web.demo.service.UserServiceSon;

@Controller
public class LoginControllerSon {
	@Autowired
	UserServiceSon userservice;
	@Autowired
	TokenServiceSon tokenservice;
	@Autowired
	CategoryService cateservice;
	@Autowired
	SystemsService systemservice;
@GetMapping("/403")
public String error() {
	return "403";
}
	
	// Login



	@PostMapping("/regis")
	public String savecustomer(@Validated @ModelAttribute("user") Users user, ModelMap model,
			@RequestParam(required = false) String pre_password) {

	

		Optional<Users> userbyusername = userservice.findByUsernameUsers(user.getUsernameUsers());
		Optional<Users> userbyemail = userservice.findByEmailUsers(user.getEmailUsers());

		if (userbyusername.isPresent()) {
			model.addAttribute("message2", "Username already exists");
			return "shop/shop-3";
		}
		if (userbyemail.isPresent()) {
			model.addAttribute("message2", "Email already exists");
			return "shop/shop-3";
		}
		if (!pre_password.equalsIgnoreCase(user.getPasswordUsers())) {
			model.addAttribute("message2", "Password not match");
			return "shop/shop-3";
		} else {
			int roles = 3;
			Users us = userservice.addUser(user, roles);
			model.addAttribute(us);
		}
		return "redirect:/shop";
	}

//	}

	@PostMapping("/forgot")
	public String forgot(Model model, @ModelAttribute("user") Users user) {
		Optional<Users> userbyemail = userservice.findByEmailUsers(user.getEmailUsers());

		if (userbyemail.isPresent()) {
			Users us= userservice.confirmEmail(user, userbyemail);
			model.addAttribute("message3", "Please check your email");
			model.addAttribute("user", us);
			return "shop/shop-3";
		} else {
			model.addAttribute("message3", "Email not exist");
			model.addAttribute("user", user);
			return "shop/shop-3";

		}

	}

	@GetMapping("/confirm-reset")
	public String confirm(@RequestParam("token") String token, Model model, Users user,@RequestParam(required = false)String mess) {

		TokenUser tokenuser = tokenservice.findByValueTokenUsers(token);
		if (mess != null && !mess.isEmpty()) {
			if (mess.equals("P")) {
				model.addAttribute("mess", "Password not match");
			}
		}
		if (tokenuser != null) {
			Optional<Users> users = userservice.findByEmailUsers(tokenuser.getUsers().getEmailUsers());
			model.addAttribute("user", users);
			model.addAttribute("token", token);
			return "shop/confirmPassword";
		} else {
			
			model.addAttribute("messageError", " The link is invalid or broken!");
			return "shop/error-confirm";
		}

	}

	@PostMapping("/change-password")
	public String change(@RequestParam("token") String token, @RequestParam("prepassword") String prepass,
			@RequestParam("password") String pass, Model model,@ModelAttribute("user")Users user) {
		TokenUser tokenuser = tokenservice.findByValueTokenUsers(token);
		String linktoken = tokenuser.getValueTokenUsers();
		Optional<Users> users = userservice.findByEmailUsers(tokenuser.getUsers().getEmailUsers());
		if (!pass.equalsIgnoreCase(prepass)) {
			model.addAttribute("mes", "Password not match");
			return "redirect:/confirm-reset?token=" + linktoken+"&mess=P";
		} else {
			userservice.channgepass(users,pass);
			return "redirect:/shop";
		}

	}

}
