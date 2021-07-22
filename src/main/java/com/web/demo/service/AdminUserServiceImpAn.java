package com.web.demo.service;
/**
 * @author An Nguyen
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.demo.entity.Users;
import com.web.demo.repository.AdminUserRepoAn;

@Service
public class AdminUserServiceImpAn implements AdminUserServiceAn {
	@Autowired
	PasswordEncoder pass;
	
	@Override
	public void deleteById(Integer id) {
		user.deleteById(id);
	}

	AdminUserRepoAn user;

	public AdminUserServiceImpAn(AdminUserRepoAn users) {
		super();
		this.user = users;
	}

	@Override
	public List<Users> findAll() {

		return user.findAll();
	}

	@Override
	public <S extends Users> Users save(S entity) {
		Users usernew=new Users();
		usernew.setIdUsers(entity.getIdUsers());
		usernew.setNameUsers(entity.getNameUsers());
		usernew.setUsernameUsers(entity.getUsernameUsers());
		if(entity.getIdUsers() != null) {
			usernew.setPasswordUsers(entity.getPasswordUsers());
		}else {
			usernew.setPasswordUsers(pass.encode(entity.getPasswordUsers()));
		}	
		usernew.setEmailUsers(entity.getEmailUsers());
		usernew.setPhoneUsers(entity.getPhoneUsers());
		
		if(entity.getImageUsers()==null||entity.getImageUsers().equals("")) {	
			usernew.setImageUsers("noavatar.png");
		}else {
			usernew.setImageUsers(entity.getImageUsers());
		}
		usernew.setAddressUsers(entity.getAddressUsers());
		usernew.setDateOfBirthday(entity.getDateOfBirthday());
		usernew.setGender(entity.getGender());
		usernew.setRole(entity.getRole());
		if(entity.getGender()==null) {
			usernew.setGender(1);
		}else {
			usernew.setGender(entity.getGender());
		}
		if(entity.getStatus()==null) {
			usernew.setStatus(1);
		}else {
			usernew.setStatus(entity.getStatus());
		}
				
		return user.save(usernew);
	}

	@Override
	public Users getById(Integer id) {
		return user.getById(id);
	}

	@Override
	public Users findByusernameUsers(String username) {
		return user.findByusernameUsers(username);
	}
	
	
	
}
