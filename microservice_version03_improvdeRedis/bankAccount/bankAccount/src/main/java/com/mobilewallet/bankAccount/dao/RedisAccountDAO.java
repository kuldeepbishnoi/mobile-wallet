package com.mobilewallet.bankAccount.dao;

import com.mobilewallet.bankAccount.entity.BankAccount;

public interface RedisAccountDAO {
    public void save(BankAccount transaction);
    public BankAccount findByUserId(String userId);     
}
