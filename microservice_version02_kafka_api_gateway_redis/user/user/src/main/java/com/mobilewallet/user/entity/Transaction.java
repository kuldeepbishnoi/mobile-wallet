package com.mobilewallet.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    
	private Integer id;
    private double amount;
    private Integer  fromWalletId;
    private Integer  toWalletId;
    private LocalDateTime dateTime;
	private String description;
    private String status;
}
