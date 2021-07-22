package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.userdetails.User;

import com.web.demo.entity.Games;
import com.web.demo.entity.Users;

public interface UserServiceSon {

	<S extends Users> S save(S entity);

	

	Optional<Users> findByEmailUsers(String emailUsers);

	Users findByusernameUsers(String username);



	Optional<Users> findByUsernameUsers(String usernameUsers);



	<S extends Users> Users addUser(Users user, int roles);



	Users confirmEmail(Users user, Optional<Users> us);



	Users channgepass(Optional<Users> u,String pass);



	Optional<Users> findById(Integer id);



	













}
