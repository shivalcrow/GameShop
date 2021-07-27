package com.web.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.demo.entity.Games;
import com.web.demo.entity.ImageData;
import com.web.demo.repository.GameRepository;
import com.web.demo.repository.GamesRepositoryPD;
import com.web.demo.repository.ImageDataRepositoryPD;

/*
 * 
 * @author PhatDat
 */
@Service
public class ImageDataServiceImpPD implements ImageDataServicePD {
	@Autowired
	ImageDataRepositoryPD imageGameRepository;
	
	@Autowired
	private GamesRepositoryPD gamesRepository;

	
	
	/*
	 * get a game image
	 * @author PhatDat
	 */
	@Override
	public ImageData getImageGame(int i) {
		
		return imageGameRepository.getById(i);
	}
	
	/*
	 * get list of the game images
	 * @author PhatDat
	 */
	@Override
	public List<ImageData> getImageList(){
		List<Games> listGame = gamesRepository.findAll();
		List<ImageData> listImage = imageGameRepository.findAll();
		List<ImageData> listImg = new ArrayList<ImageData>();
		Boolean flag = false;
		for (Games g : listGame) {
			for (ImageData img : listImage) {
				if(g.getIdGame() == img.getGames().getIdGame() && !flag) {			
					listImg.add(img);
					flag = true;
				}
				
			}
			flag = false;
		}
		
		return listImg;
	}
	
	/*
	 * get a game's list of images
	 * @author PhatDat
	 */
	@Override
	public List<ImageData> getImageDetailGame(int id){
		return imageGameRepository.findImageGame(id);
	}

	/*
	 * get a game's list of images
	 * @author PhatDat
	 */
	@Override
	public List<ImageData> getRelatedImageList(int i1, int i2) {
		return this.imageGameRepository.findRecommendGame(i1, i2);
		
	}
	
	
	
	
}
