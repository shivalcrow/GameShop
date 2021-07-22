package com.web.demo.controller;
/**
 * @author NguyenHuuSon
 */
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.demo.entity.Users;
import com.web.demo.service.UserServiceSon;



@Controller
public class UsersImageController{
@Autowired
private UserServiceSon user;

@RequestMapping(value = "getimguser/{name}",method = RequestMethod.GET)
@ResponseBody
public ResponseEntity<ByteArrayResource> dowloadLinkImage(@PathVariable String name,Model model){
	Optional<Users> sp=user.findByUsernameUsers(name);
	if(sp.isPresent()) {
		Users u=sp.get();
		model.addAttribute("imgUser",u);
		try {
			Path filename=Paths.get("images",u.getImageUsers());
			byte[] buffer=Files.readAllBytes(filename);
			ByteArrayResource bsr=new ByteArrayResource(buffer);
			return ResponseEntity.ok()
					.contentLength(buffer.length)
					.contentType(MediaType.parseMediaType("image/jpg"))
					.body(bsr);
		}catch (Exception e) {
		e.printStackTrace();
		}
	}
	return ResponseEntity.badRequest().build();
}
}
