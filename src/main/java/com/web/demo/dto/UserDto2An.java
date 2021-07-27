package com.web.demo.dto;
/**
 * @author An Nguyen
 */
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.web.demo.entity.Bill;
import com.web.demo.entity.Blog;
import com.web.demo.entity.CommentBlog;
import com.web.demo.entity.CommentGame;
import com.web.demo.entity.Games;
import com.web.demo.entity.TokenUser;

public class UserDto2An {
	
	private Integer idUsers;
	private String nameUsers;
	private String usernameUsers;
	private String passwordUsers;
	private String emailUsers;
	private String phoneUsers;
	private String imageUsers;
	private String addressUsers;
	private Date dateOfBirthday;
	private Integer gender;
	private Integer status;
//	private Set<TokenUser> tokenUsers = new HashSet<TokenUser>(0);
//	private Set<Bill> bills = new HashSet<Bill>(0);
//	private Set<Blog> blogs = new HashSet<Blog>(0);
//	private Set<CommentBlog> commentBlogs = new HashSet<CommentBlog>(0);
//	private Set<CommentGame> commentGames = new HashSet<CommentGame>(0);
//	private Set<Games> GamesActive = new HashSet<Games>(0);
	public Integer getIdUsers() {
		return idUsers;
	}
	public void setIdUsers(Integer idUsers) {
		this.idUsers = idUsers;
	}
	public String getNameUsers() {
		return nameUsers;
	}
	public void setNameUsers(String nameUsers) {
		this.nameUsers = nameUsers;
	}
	public String getUsernameUsers() {
		return usernameUsers;
	}
	public void setUsernameUsers(String usernameUsers) {
		this.usernameUsers = usernameUsers;
	}
	public String getPasswordUsers() {
		return passwordUsers;
	}
	public void setPasswordUsers(String passwordUsers) {
		this.passwordUsers = passwordUsers;
	}
	public String getEmailUsers() {
		return emailUsers;
	}
	public void setEmailUsers(String emailUsers) {
		this.emailUsers = emailUsers;
	}
	public String getPhoneUsers() {
		return phoneUsers;
	}
	public void setPhoneUsers(String phoneUsers) {
		this.phoneUsers = phoneUsers;
	}
	public String getImageUsers() {
		return imageUsers;
	}
	public void setImageUsers(String imageUsers) {
		this.imageUsers = imageUsers;
	}
	public String getAddressUsers() {
		return addressUsers;
	}
	public void setAddressUsers(String addressUsers) {
		this.addressUsers = addressUsers;
	}
	public Date getDateOfBirthday() {
		return dateOfBirthday;
	}
	public void setDateOfBirthday(Date dateOfBirthday) {
		this.dateOfBirthday = dateOfBirthday;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
//	public Set<TokenUser> getTokenUsers() {
//		return tokenUsers;
//	}
//	public void setTokenUsers(Set<TokenUser> tokenUsers) {
//		this.tokenUsers = tokenUsers;
//	}
//	public Set<Bill> getBills() {
//		return bills;
//	}
//	public void setBills(Set<Bill> bills) {
//		this.bills = bills;
//	}
//	public Set<Blog> getBlogs() {
//		return blogs;
//	}
//	public void setBlogs(Set<Blog> blogs) {
//		this.blogs = blogs;
//	}
//	public Set<CommentBlog> getCommentBlogs() {
//		return commentBlogs;
//	}
//	public void setCommentBlogs(Set<CommentBlog> commentBlogs) {
//		this.commentBlogs = commentBlogs;
//	}
//	public Set<CommentGame> getCommentGames() {
//		return commentGames;
//	}
//	public void setCommentGames(Set<CommentGame> commentGames) {
//		this.commentGames = commentGames;
//	}
	
//	public Set<Games> getGamesActive() {
//		return GamesActive;
//	}
//	public void setGamesActive(Set<Games> gamesActive) {
//		GamesActive = gamesActive;
//	}
}
