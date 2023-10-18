package com.mobilewallet.user.entity;

import java.util.UUID;

// import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

// @NoArgsConstructor
// @AllArgsConstructor
@Data
public class Transaction {

	private String id;
    private Integer amount;
    private String  fromWalletId;
    private String  toWalletId;
	private String description;
    private String status;

    public Transaction() {
        this.id = UUID.randomUUID().toString(); // Generate a random UUID as the primary key
    }

    public Transaction(String id, Integer amount, String fromWalletId, String toWalletId, String description, String status) {
        this.id = id;
        this.amount = amount;
        this.fromWalletId = fromWalletId;
        this.toWalletId = toWalletId;
        this.description = description;
        this.status = status;
    }
}
