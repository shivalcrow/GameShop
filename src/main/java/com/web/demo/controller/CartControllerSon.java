package com.web.demo.controller;
/**
 * @author NguyenHuuSon
 */

import java.security.Principal;
/**
 * @author NguyenHuuSon
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.web.demo.config.PaypalPaymentIntent;
import com.web.demo.config.PaypalPaymentMethod;
import com.web.demo.config.PaypalUtils;
import com.web.demo.config.WebUtils;
import com.web.demo.dto.CartDTOSon;
import com.web.demo.entity.Bill;
import com.web.demo.entity.Games;
import com.web.demo.entity.Users;
import com.web.demo.service.AdminGameServiceAn;
import com.web.demo.service.BillDetailServiceSon;
import com.web.demo.service.BillServiceSon;
import com.web.demo.service.PayPalService;
import com.web.demo.service.UserServiceSon;
@Controller
public class CartControllerSon {
	@Autowired
	UserServiceSon userservice;
	@Autowired
	private PayPalService paypalService;
	@Autowired
	AdminGameServiceAn gameservice;
	@Autowired
	BillServiceSon billservice;
	@Autowired
	BillDetailServiceSon billdetailservice;
	@GetMapping("/cart")
	public String indexcart(Model model,Users user,Principal principal,HttpSession session) {
		model.addAttribute("user", user);
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			Users us = userservice.findByusernameUsers(loginedUser.getUsername());
			session.setAttribute("userinfoname", us.getNameUsers());
			session.setAttribute("userinfoemail", us.getEmailUsers());
			session.setAttribute("userinfoid", us.getIdUsers());
			session.setAttribute("userinfophone", us.getPhoneUsers());
			System.out.println(session.getAttribute("userinfoname") + "a" + session.getAttribute("userinfoemail"));
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "shop/checkout-1";
	}
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
	private Logger log = LoggerFactory.getLogger(getClass());
	@GetMapping("cart/add/{id}")
	public String  addcart(Model model,HttpSession session,@PathVariable int id,HttpServletRequest req) {
		String fe=req.getHeader("REFERER");
		System.out.println(fe);
		@SuppressWarnings("unchecked")
		HashMap<Integer, CartDTOSon>Cartitems=(HashMap<Integer, CartDTOSon>)session.getAttribute("mycartitem");
		if(Cartitems==null) {
			Cartitems=new HashMap<Integer, CartDTOSon>();
			
		}
		Optional<Games> games=gameservice.findById(id);
		if(games.isPresent()) {
			CartDTOSon cart=new CartDTOSon(games.get());
			
			Cartitems.put(id, cart);
			
		}
		session.setAttribute("mycartitem", Cartitems);
		session.setAttribute("mycarttotal",totalPrice(Cartitems));
		session.setAttribute("mycartnum", Cartitems.size());
		return "redirect:"+fe;
	}
	public double totalPrice(HashMap<Integer, CartDTOSon> cartitems) {
		int count=0;
		for(Map.Entry<Integer,CartDTOSon> list: cartitems.entrySet()) {
			
				count += list.getValue().getGames().getPriceFix();
			
		}
		return count;
	}
	@GetMapping("cart/remove/{id}")
	public String remove(Model model, HttpSession session,@PathVariable("id") Integer id) {
		@SuppressWarnings("unchecked")
		HashMap<Integer, CartDTOSon> cartitems=(HashMap<Integer, CartDTOSon>)session.getAttribute("mycartitem");
		if(cartitems==null) {
			cartitems=new HashMap<>();
		}
		if(cartitems.containsKey(id)) {
			cartitems.remove(id);
		}
		session.setAttribute("mycartitem", cartitems);
		session.setAttribute("mycarttotal",totalPrice(cartitems));
		session.setAttribute("mycartnum", cartitems.size());
		return "redirect:/cart";
	}
	@PostMapping("/pay")
	public String pay(HttpServletRequest request,HttpSession session,Model model  ){
		String cancelUrl = PaypalUtils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = PaypalUtils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		double price=(double) session.getAttribute("mycarttotal");
		if(session.getAttribute("userinfoname")==null) {
			
			return "redirect:/shop?message=loginreq";
		}else {
		try {
			Payment payment = paypalService.createPayment(
					price, 
					"USD", 
					PaypalPaymentMethod.paypal, 
					PaypalPaymentIntent.sale,
					"payment description", 
					cancelUrl, 
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
	return "redirect:/";
		}
	}
	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay(){
		return "redirect:/cart";
	}
	@SuppressWarnings("unchecked")
	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,HttpSession session,@ModelAttribute("bill")Bill bill){
		HashMap<Integer, CartDTOSon> cartitems=(HashMap<Integer, CartDTOSon>) session.getAttribute("mycartitem");
		if(cartitems==null) {
			cartitems=new HashMap<>();
		}
		try {
			Users user=userservice.findByusernameUsers(session.getAttribute("userinfoname").toString());
			System.out.println("user: "+user.getNameUsers());
			double price=(double) session.getAttribute("mycarttotal");
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				Bill addbill= billservice.save(bill,user ,price );
				for(Map.Entry<Integer, CartDTOSon>entry:cartitems.entrySet()) {
					Optional<Games> game=gameservice.findById(entry.getValue().getGames().getIdGame());
					billdetailservice.addbilldetail(addbill, game.get());
					//
					game.get().getUsersActive().add(user);
					gameservice.save(game.get());
				}

				cartitems=new HashMap<>();
				session.setAttribute("mycartitem", cartitems);
				session.setAttribute("mycarttotal",0);
				session.setAttribute("mycartnum", 0);


				return "redirect:/shop";


			


				
				

				




			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/cart";
	}
}
