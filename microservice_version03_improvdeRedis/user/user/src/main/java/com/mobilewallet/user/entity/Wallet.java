package com.mobilewallet.user.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "wallet")
public class Wallet {

	@Id
	private String id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "balance")
	private Integer balance;

	// no args constructor
	public Wallet() {
		this.id = UUID.randomUUID().toString(); // Generate a random UUID as the primary key
	}

	// all args constructor
	public Wallet(String id, String userId, Integer balance) {
		this.id = id;
		this.userId = userId;
		this.balance = balance;
	}
}
