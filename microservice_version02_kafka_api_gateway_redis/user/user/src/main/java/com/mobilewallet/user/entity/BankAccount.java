package com.mobilewallet.user.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankAccount {
    
    private Integer id;
    private Integer userId;
    private String bankName;
    private String IFSC;
    private String accountNo;
}
