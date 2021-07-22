package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.demo.entity.TokenUser;
import com.web.demo.entity.Users;
import com.web.demo.repository.TokenRepositorySon;

@Service
public class TokenServiceImpSon implements TokenServiceSon{
	
	

	
	@Override
	public TokenUser findByUsers(Users users) {
		return confirmtoken.findByUsers(users);
	}
	@Override
	public TokenUser findByUsers(Optional<Users> users) {
		return confirmtoken.findByUsers(users);
	}
	@Override
	public <S extends TokenUser> S save(S entity) {
		return confirmtoken.save(entity);
	}
	@Override
	public TokenUser findByValueTokenUsers(String valueTokenUsers) {
		return confirmtoken.findByValueTokenUsers(valueTokenUsers);
	}
	@Autowired
	TokenRepositorySon confirmtoken;
}
