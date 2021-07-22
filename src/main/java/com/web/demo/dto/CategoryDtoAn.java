package com.web.demo.dto;
/**
 * @author An Nguyen
 */
import java.util.HashSet;
import java.util.Set;

public class CategoryDtoAn {
	private Integer idCategory;
	private String nameCategory;
//	private Set<GamesDtoAn2> games = new HashSet<GamesDtoAn2>(0);
	public Integer getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
//	public Set<GamesDtoAn2> getGames() {
//		return games;
//	}
//	public void setGames(Set<GamesDtoAn2> games) {
//		this.games = games;
//	}
	
}
