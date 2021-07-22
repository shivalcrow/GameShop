package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */


import java.util.Optional;

import com.web.demo.entity.TokenUser;
import com.web.demo.entity.Users;


public interface TokenServiceSon {

	TokenUser findByValueTokenUsers(String valueTokenUsers);

	<S extends TokenUser> S save(S entity);

	TokenUser findByUsers(Optional<Users> users);

	TokenUser findByUsers(Users users);



	
}
