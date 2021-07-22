package com.web.demo.controller;

/**
 * @author Nguyen Phuong
 */
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.demo.config.WebUtilsAn;
import com.web.demo.dto.SlideShowDTO;
import com.web.demo.entity.Games;
import com.web.demo.entity.SlideShow;
import com.web.demo.service.GamesService;
import com.web.demo.service.SlideShowService;



@Controller
public class SlideShowController{
@Autowired
private SlideShowService slideshowservice;
@Autowired
private GamesService gameservice;

@GetMapping("admin/slide")
public String index(Model model,Principal principal) {
	SlideShowDTO slide=new SlideShowDTO();
	model.addAttribute("slidedto", slide);
	List<SlideShow> listslide=slideshowservice.findAll();
	model.addAttribute("listslide", listslide);
	List<Games>  listgame= gameservice.findAll();
	model.addAttribute("listgames", listgame);
	if (principal != null) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtilsAn.toStringManager(loginedUser);
		model.addAttribute("userInfo", userInfo);
	}
	return "admin/slideshow";
	
}
@PostMapping("slide/add")
public String AddorUpdate(Model model,@ModelAttribute("slidedto")SlideShowDTO slide) {
	Path path=Paths.get("images");
	try(InputStream inputstream=slide.getImage().getInputStream()) {
		
		Files.copy(inputstream, path.resolve(slide.getImage().getOriginalFilename()),
				StandardCopyOption.REPLACE_EXISTING);
		String filename=slide.getImage().getOriginalFilename();
		System.out.println(filename);
		System.out.println(path);
	}catch (Exception e) {
		e.printStackTrace();
	}
	SlideShow slideshow=new SlideShow();
	Games gm=new Games();
	gm.setIdGame(slide.getGames());
	slideshow.setGames(gm);
	if(!slide.getImage().isEmpty()&&slide.getImage().getOriginalFilename()!=null) {
		slideshow.setImage(slide.getImage().getOriginalFilename());
		model.addAttribute("message", "Add");
		slideshowservice.save(slideshow);
	}else {
		Optional<SlideShow> sl=slideshowservice.findById(slide.getIdSlideShow());
		if(sl.isPresent()) {
			
			slideshow.setIdSlideShow(sl.get().getIdSlideShow());
			slideshow.setImage(sl.get().getImage());
			model.addAttribute("message", "Update");
			System.out.println(slideshow.getIdSlideShow());
			System.out.println(slideshow.getImage());
			System.out.println(slideshow.getGames().getIdGame());
			
			slideshowservice.save(slideshow);
			
		}
	}
	
	
	model.addAttribute("slidedto", slide);
	
	
	return "redirect:/admin/slide";
	
}
@GetMapping("slideEdit/{id}")
public String editslide(@PathVariable(name = "id")Integer id,Model model, Principal principal) {
	Optional<SlideShow> slide=slideshowservice.findById(id);
	if (principal != null) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtilsAn.toStringManager(loginedUser);
		model.addAttribute("userInfo", userInfo);
	}
	if (slide.isPresent()) {
		List<Games>  listgame= gameservice.findAll();
		model.addAttribute("listgames", listgame);
		System.out.println(slide.get().getImage());
		model.addAttribute("slidedto", slide.get());
		return "admin/slideshow";
	}else {
		return "redirect:/admin/slide";
		
	}
	
	
}
@GetMapping("slideDel/{id}")
public String del(@PathVariable(name = "id")Integer id) {
	slideshowservice.deleteById(id);
	return"redirect:/admin/slide";
}

}
