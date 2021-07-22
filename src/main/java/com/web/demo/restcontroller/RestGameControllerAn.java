package com.web.demo.restcontroller;
/**
 * @author An Nguyen
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.demo.converter.CategoryConverterAn;
import com.web.demo.dto.GamesDtoAn;
import com.web.demo.entity.Games;
import com.web.demo.service.AdminGameServiceAn;
@RestController
public class RestGameControllerAn {
	@Autowired
	AdminGameServiceAn gameService;
	@GetMapping("/api/game")
	public ResponseEntity<?> getListgame(){
		System.out.println("/api/game");
		List<Games> list=gameService.findAll();
		List<GamesDtoAn> listgameDto = CategoryConverterAn.getInstance().togameDtoList(list);
		
		return ResponseEntity.ok(listgameDto);
		
	}
//	@PostMapping("/api/addgame")
//	public  ResponseEntity<Games> addgame(@RequestBody Games game){	
//		Games us = gameService.save(game);
//		return new ResponseEntity<Games>(us,HttpStatus.OK);
//	}
//	@GetMapping("/api/edit/{id}")
//	public Games edit(@PathVariable(name="id")Integer id){
//		Optional<Games> gm=gameService.findById(id);
//		
//		return  gm.get();
//	}
	@DeleteMapping("/api/deletegame/{id}")
	public void delete(@PathVariable Integer id){
			 gameService.deleteById(id);
	}
}
