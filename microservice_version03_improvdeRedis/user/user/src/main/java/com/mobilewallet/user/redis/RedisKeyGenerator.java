package com.mobilewallet.user.redis;

import org.springframework.stereotype.Component;

import com.mobilewallet.user.entity.User;
import com.mobilewallet.user.entity.Wallet;

@Component
public class RedisKeyGenerator {
    public String generateMobileNoKey(String mobileNo) {
        return "user:mobileNo#" + mobileNo;
    }

    public String generateEmailKey(String email) {
        return "user:email#" + email;
    }

    public String generateUserIdKey(String userId) {
        return "user:userId#" + userId;
    }

    public String generateWalletIdKey(String walletId) {
        return "user:userId#" + walletId;
    }
}
