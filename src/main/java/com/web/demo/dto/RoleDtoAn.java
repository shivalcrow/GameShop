package com.web.demo.dto;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author An Nguyen, thanks to Tan
 *
 */
public class RoleDtoAn {
	
	private Integer idRole;
	private String nameRole;
	private Set<UserDto2An> userses = new HashSet<UserDto2An>(0);
	public Integer getIdRole() {
		return idRole;
	}
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	public String getNameRole() {
		return nameRole;
	}
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	public Set<UserDto2An> getUserses() {
		return userses;
	}
	public void setUserses(Set<UserDto2An> userses) {
		this.userses = userses;
	}
	
}
