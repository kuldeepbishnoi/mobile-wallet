package com.mobilewallet.user.entity;

import java.util.UUID;

import lombok.Data;


// @NoArgsConstructor
// @AllArgsConstructor
@Data
public class BankAccount {
    
    private String id;
    private String userId;
    private String bankName;
    private String IFSC;
    private String accountNo;

    // no default constructor
    public BankAccount() {
        this.id = UUID.randomUUID().toString();
    }

    // all args constructor
    public BankAccount(String id, String userId, String bankName, String IFSC, String accountNo) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.bankName = bankName;
        this.IFSC = IFSC;
        this.accountNo = accountNo;
    }
}
