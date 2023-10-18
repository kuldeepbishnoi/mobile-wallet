package com.mobilewallet.user.redis;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import com.mobilewallet.user.entity.Role;
import com.mobilewallet.user.entity.User;
import com.mobilewallet.user.entity.Wallet;

@Component
public class RedisSerializerAndDeserializer {

    public Map<String, String> userSerializer(User user) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("id", user.getId());
        userMap.put("mobileNo", user.getMobileNo());
        userMap.put("email", user.getEmail());
        userMap.put("password", user.getPassword());
        userMap.put("role", "USER");
        userMap.put("firstName", user.getFirstName());
        userMap.put("lastName", user.getLastName());
        return userMap;
    }

    public User userDeserializer(Map<String, String> userMap) {
        User user = new User();
        user.setId(userMap.get("id"));
        user.setMobileNo(userMap.get("mobileNo"));
        user.setEmail(userMap.get("email"));
        user.setPassword(userMap.get("password"));
        user.setRole(Role.USER);
        user.setFirstName(userMap.get("firstName"));
        user.setLastName(userMap.get("lastName"));
        return user;
    }

    public Map<String, String> walletSerializer(Wallet wallet) {
        Map<String, String> walletMap = new HashMap<>();
        walletMap.put("id", wallet.getId());
        walletMap.put("userId", wallet.getUserId());
        walletMap.put("balance", String.valueOf(wallet.getBalance()));
        return walletMap;
    }

    public Wallet walletDeserializer(Map<String, String> walletMap) {
        Wallet wallet = new Wallet();
        wallet.setId(walletMap.get("id"));
        wallet.setUserId(walletMap.get("userId"));
        wallet.setBalance(Integer.parseInt(walletMap.get("balance")));
        return wallet;
    }
    
}
