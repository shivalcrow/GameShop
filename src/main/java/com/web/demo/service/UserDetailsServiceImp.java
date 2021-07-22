package com.web.demo.service;
/**
 * @author NguyenHuuSon
 */
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.web.demo.entity.Users;
import com.web.demo.repository.UsersRepositorySon;



@Service
public class UserDetailsServiceImp implements UserDetailsService{
	@Autowired
	private UsersRepositorySon usersRepository;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=usersRepository.findByusernameUsers(username);
		if(user==null) throw new UsernameNotFoundException("Login Fail");
		
		List<GrantedAuthority> authoritie =new ArrayList<GrantedAuthority>();
		String rolename=user.getRole().getNameRole();
		authoritie.add(new SimpleGrantedAuthority(rolename));
		System.out.println(user.getEmailUsers());
		System.out.println(user.getUsernameUsers());
		System.out.println(user.getPasswordUsers());
		boolean enabled=true;
		boolean accountNonExpired=true;
		boolean credentialIsNonExpired= true;
		boolean accountNonLock=true;
		return new User(user.getUsernameUsers(),user.getPasswordUsers(),enabled,accountNonExpired,credentialIsNonExpired,accountNonLock,authoritie);	
	}
	

}
