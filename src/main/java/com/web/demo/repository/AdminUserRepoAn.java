package com.web.demo.repository;
/**
 * @author An Nguyen
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.demo.entity.Users;
@Repository
public interface AdminUserRepoAn extends JpaRepository<Users, Integer> {

	Users findByusernameUsers(String username);
	
}
