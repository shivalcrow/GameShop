package com.web.demo.service;
/**
 * @author An Nguyen
 */
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;

import com.web.demo.entity.Users;

public interface AdminUserServiceAn {
	
	
	List<Users> findAll();

	<S extends Users> Users save(S entity);

	Users getById(Integer id);

	void deleteById(Integer id);

	Users findByusernameUsers(String username);
	
}
