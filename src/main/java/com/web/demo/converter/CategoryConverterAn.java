package com.web.demo.converter;
/**
 * @author An Nguyen thanks to Tan
 */
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.web.demo.dto.CategoryDtoAn;
import com.web.demo.dto.GamesDtoAn;
import com.web.demo.entity.Category;
import com.web.demo.entity.Games;

public class CategoryConverterAn {

	private ModelMapper mapper = new ModelMapper();
	
	private static CategoryConverterAn cateConverter;
	
	private CategoryConverterAn() {};
	
	public static CategoryConverterAn getInstance() {
		if(cateConverter == null) {
			cateConverter = new CategoryConverterAn();
		}
		return cateConverter;
	}
	public GamesDtoAn toGameDto(Games entity) {
		return mapper.map(entity, GamesDtoAn.class);
	}
	public List<GamesDtoAn> togameDtoList(List<Games> list) {
		return list
				.stream()
				.map(game -> { return toGameDto(game);})
				.collect(Collectors.toList());
	}
	
	public CategoryDtoAn toCateDto(Category entity) {
		return mapper.map(entity, CategoryDtoAn.class);
	}
	public List<CategoryDtoAn> tocateDtoList(List<Category> list) {
		return list
				.stream()
				.map(cate -> { return toCateDto(cate);})
				.collect(Collectors.toList());
	}
}
