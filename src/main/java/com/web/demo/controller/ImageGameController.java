package com.web.demo.controller;
/**
 * @author HuuSon
 */
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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

import com.web.demo.entity.ImageData;
import com.web.demo.entity.SlideShow;
import com.web.demo.service.GameImageServiceSon;
import com.web.demo.service.SlideShowService;



@Controller
public class ImageGameController{
@Autowired
private GameImageServiceSon gameimageservice;

@RequestMapping(value = "getgameimage/{id}",method = RequestMethod.GET)
@ResponseBody
public ResponseEntity<ByteArrayResource> dowloadLinkImage(@PathVariable int id){
	Optional<ImageData> idata=gameimageservice.findById(id);
	if(idata.isPresent()) {
		ImageData slide=idata.get();
		try {
			Path filename=Paths.get("images",slide.getNameImage());
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
