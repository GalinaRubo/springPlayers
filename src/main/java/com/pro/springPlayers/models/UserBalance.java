package com.pro.springPlayers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "balances")
public class UserBalance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Integer balance;
	@OneToOne
	@JoinColumn(name = "user_id")
	public User user;

	public UserBalance() {
	}

	public UserBalance(Integer balance, User user) {
		super();
		this.balance = balance;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
