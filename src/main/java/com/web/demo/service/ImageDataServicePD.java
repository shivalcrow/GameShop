package com.web.demo.service;

import java.util.List;

import com.web.demo.entity.ImageData;

/*
 *
 * @author PhatDat
 */
public interface ImageDataServicePD {
	public ImageData getImageGame(int i);
	public List<ImageData> getImageDetailGame(int i);
	public List<ImageData> getImageList();
	public List<ImageData> getRelatedImageList(int i);
}
