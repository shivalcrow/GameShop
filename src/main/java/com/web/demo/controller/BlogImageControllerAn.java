package com.web.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.demo.entity.Blog;
import com.web.demo.service.BlogService;

/**
 * 
 * @author AnNguyen
 *
 */
@Controller
public class BlogImageControllerAn {
	
	@Autowired
	BlogService blogService;
	
	@RequestMapping(value = "getblogimage/{id}",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> blogImage(@PathVariable int id){
		Optional<Blog> blog = blogService.findById(id);
		Blog bl = blog.get();
		String imgblog = bl.getImageBlog();
		if(imgblog != null || !imgblog.isEmpty()) {
			try {
				Path filename=Paths.get("images",imgblog);
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
