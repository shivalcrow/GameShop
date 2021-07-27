package com.web.demo.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.web.demo.dto.UsersDtoAn;
import com.web.demo.entity.Users;

/**
 * 
 * @author AnNguyen
 *
 */
public class UsersConverterAn {
	
	private ModelMapper mapper = new ModelMapper();
	
	private static UsersConverterAn userConverter;
	
	private UsersConverterAn() {};
	
	public static UsersConverterAn getInstance() {
		if(userConverter == null) {
			userConverter = new UsersConverterAn();
		}
		return userConverter;
	}
	public UsersDtoAn toUserDto(Users entity) {
		return mapper.map(entity, UsersDtoAn.class);
	}
	public List<UsersDtoAn> touserDtoList(List<Users> list) {
		return list
				.stream()
				.map(user -> { return toUserDto(user);})
				.collect(Collectors.toList());
	}
}
