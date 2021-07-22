package com.web.demo.controller;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * @author An Nguyen
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.web.demo.config.WebUtilsAn;
import com.web.demo.entity.Bill;
import com.web.demo.entity.Category;
import com.web.demo.entity.Games;
import com.web.demo.entity.Systems;
import com.web.demo.entity.Users;
import com.web.demo.service.AdminBillServiceAn;
import com.web.demo.service.AdminGameServiceAn;
import com.web.demo.service.AdminUserServiceAn;
import com.web.demo.service.CategoryService;
import com.web.demo.service.ImageServiceAn;
import com.web.demo.service.SystemsService;

@Controller
public class AdminControllerAn {
	
	@Autowired
	AdminGameServiceAn gameService;
	
	@Autowired
	AdminUserServiceAn userService;
	
	@Autowired
	AdminBillServiceAn billService;
	
	@Autowired
	SystemsService systemService;
	
	@Autowired
	ImageServiceAn imgService;
	
	@Autowired
	PasswordEncoder pass;
	
	@GetMapping("admin")
	public String adminindex(Model model, Principal principal) {
		List<Games> topgame = gameService.findAllTop();
		model.addAttribute("topgame", topgame);
		List<Bill> topuser = billService.findAllTop();
		model.addAttribute("topuser", topuser);
		for(Bill b:topuser) {
			System.out.println(b.getUsers().getNameUsers());
		}
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtilsAn.toStringManager(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		//access page times and download
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
			LocalDate localDate = LocalDate.now();
			String date = dtf.format(localDate);
			//model.addAttribute("todaycalendar", date);
			Systems sys = systemService.findByDateLike(date);
			model.addAttribute("date", sys);
			//get yesterday
			LocalDate previous = localDate.minus(Period.ofDays(1));
			System.out.println(dtf.format(previous));
			String yesterday = dtf.format(previous);
			Systems yes = systemService.findByDateLike(yesterday);
			if(yes != null) {
				model.addAttribute("yesterday", yes);
			}else {
				Systems ye = new Systems();
				ye.setDowloadSystem(0);
				ye.setViewsSystem(0);
				model.addAttribute("yesterday",ye);
			}
		//purchases
			long count = billService.findCount(date);
			model.addAttribute("purchases", count);
			long countys = billService.findCount(yesterday);
			model.addAttribute("purchasesys", countys);
		//total revenue
			String total = billService.findTotalPrice(date);
			if(total != null) {
				model.addAttribute("total", total);
			}else {
				total = "0";
				model.addAttribute("total", total);
			}
			String totalys = billService.findTotalPrice(yesterday);
			if(totalys != null) {
				model.addAttribute("totalys", totalys);
			}else {
				totalys = "0";
				model.addAttribute("totalys", totalys);
			}
			
		return "admin/index";
	}
	@PostMapping("/chooseday")
	public String chooseday(Model model, Principal principal, @RequestParam("chooseday") String dates) {
		List<Games> topgame = gameService.findAllTop();
		model.addAttribute("topgame", topgame);
		List<Bill> topuser = billService.findAllTop();
		model.addAttribute("topuser", topuser);
		for(Bill b:topuser) {
			System.out.println(b.getUsers().getNameUsers());
		}
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtilsAn.toStringManager(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		System.out.println(dates);
		/* this day*/
		//access
		Systems sys = systemService.findByDateLike(dates);
		if(sys != null) {
			model.addAttribute("date", sys);
		}else {
			Systems system = new Systems();
			system.setViewsSystem(0);
			system.setDowloadSystem(0);
			model.addAttribute("date", system);
		}
		//purchases
		long count = billService.findCount(dates);
		model.addAttribute("purchases", count);
		//total revenue
		String total = billService.findTotalPrice(dates);
		if(total != null) {
			model.addAttribute("total", total);
		}else {
			total = "0";
		}
		/* previous day*/
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateformat = format.parse(dates);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateformat); 
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			Date datebefore = calendar.getTime();
			String yesterday = format.format(datebefore); 
			System.out.println(yesterday);
			//access and download yesterday
			Systems yes = systemService.findByDateLike(yesterday);
			if(yes != null) {
				model.addAttribute("yesterday", yes);
			}else {
				Systems system = new Systems();
				system.setViewsSystem(0);
				system.setDowloadSystem(0);
				model.addAttribute("yesterday", system);
			}
			//purchases yesterday
			long countys = billService.findCount(yesterday);
			model.addAttribute("purchasesys", countys);
			//total revenue yesterday
			String totalys = billService.findTotalPrice(yesterday);
			if(totalys != null) {
			model.addAttribute("totalys", totalys);
			}else {
				totalys = "0";
				model.addAttribute("totalys", totalys);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/index";
	}
	//Users
	@GetMapping("admin/listusers")
	public String userlist(Model model, Principal principal) {
		System.out.println("Admin/listuser");
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtilsAn.toStringManager(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "admin/listuser";
	}
	@GetMapping("admin/listcustomers")
	public String listcustomer(Model model, Principal principal) {
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtilsAn.toStringManager(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "admin/customer";
	}
	@GetMapping("admin/infor")
	public String infor(Model model, Principal principal) {
		String userInfo = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = WebUtilsAn.toStringManager(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		Users user = userService.findByusernameUsers(userInfo);
		model.addAttribute("useredit", user);
		return "admin/infor";
	}
	@PostMapping("/saveinfor")
	public String saveinfor(@ModelAttribute("useredit") Users user, Principal principal) {
		String userInfo = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = WebUtilsAn.toStringManager(loginedUser);
		}
		Users users = userService.findByusernameUsers(userInfo);
		users.setAddressUsers(user.getAddressUsers());
		users.setDateOfBirthday(user.getDateOfBirthday());
		users.setEmailUsers(user.getEmailUsers());
		users.setNameUsers(user.getNameUsers());
		users.setPhoneUsers(user.getPhoneUsers());
		userService.save(users);
		return "redirect:/admin/infor";
		}
	@PostMapping("/saveavatar")
	public String saveavatar(@RequestParam("file") MultipartFile file, Principal principal) {
		String userInfo = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = WebUtilsAn.toStringManager(loginedUser);
		}
		Users users = userService.findByusernameUsers(userInfo);
		imgService.store(file);
		String nameimg = file.getOriginalFilename();
		users.setImageUsers(nameimg);
		userService.save(users);
		return "redirect:/admin/infor";
		}
	@PostMapping("/savepassword")
	public String savepassword(Model model, @RequestParam("oldpass") String oldpass, @RequestParam("newpasswordUsers") String newpass, Principal principal) {
		String userInfo = null;
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			userInfo = WebUtilsAn.toStringManager(loginedUser);
		}
		Users users = userService.findByusernameUsers(userInfo);
		System.out.println(oldpass);
		System.out.println(newpass);
		String opass = users.getPasswordUsers();
		String encodenewpass = pass.encode(newpass);
		if(pass.matches(oldpass, opass)) {
			users.setPasswordUsers(encodenewpass);
			userService.save(users);
			return "redirect:/admin/infor";
		}else {
			System.out.println("wrong password");
			String mess = "Old Password is not correct !!!";
			model.addAttribute("message", mess);
			model.addAttribute("useredit", users);
			model.addAttribute("userInfo", userInfo);
			return "admin/infor";
		}
		
		}
	@GetMapping("admin/maintenance")
	public String maintenance() {
		return "admin/maintenance";
	}
}
