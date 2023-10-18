package com.mobilewallet.bankAccount.redis;

import org.springframework.stereotype.Component;

// import com.mobilewallet.bankAccount.entity.BankAccount;

@Component
public class RedisKeyGenerator {
    public String generateUserIdKey(String userId) {
        return "bankAccount:userId#" + userId;
    }
}
