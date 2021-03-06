package com.web.demo.entity;
// Generated Jul 5, 2021, 11:28:02 AM by Hibernate Tools 5.0.6.Final

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TokenUser generated by hbm2java
 */
@Entity
@Table(name = "token_user", catalog = "megalodondb")
public class TokenUser implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id_token_users", unique = true, nullable = false)
	private Integer idTokenUsers;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Id_users", nullable = false)
	private Users users;
	@Column(name = "value_token_users", length = 45)
	private String valueTokenUsers;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date", length = 19)
	private Date date;

	public TokenUser() {
	}

	public TokenUser(Users users) {
		this.users = users;
		date = new Date();
	     valueTokenUsers = UUID.randomUUID().toString();
	}
	
	public Integer getIdTokenUsers() {
		return this.idTokenUsers;
	}

	public void setIdTokenUsers(Integer idTokenUsers) {
		this.idTokenUsers = idTokenUsers;
	}


	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}


	public String getValueTokenUsers() {
		return this.valueTokenUsers;
	}

	public void setValueTokenUsers(String valueTokenUsers) {
		this.valueTokenUsers = valueTokenUsers;
	}

	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
