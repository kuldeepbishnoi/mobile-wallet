package com.mobilewallet.bankAccount.redis;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

// import com.mobilewallet.user.entity.Role;
// import com.mobilewallet.user.entity.User;
import com.mobilewallet.bankAccount.entity.BankAccount;

@Component
public class RedisSerializerAndDeserializer {


    public Map<String, String> bankAccountSerializer(BankAccount bankAccount) {
        Map<String, String> bankAccountMap = new HashMap<>();
        bankAccountMap.put("id", bankAccount.getId());
        bankAccountMap.put("userId", bankAccount.getUserId());
        bankAccountMap.put("bankName", bankAccount.getBankName());
        bankAccountMap.put("accountNumber", bankAccount.getAccountNo());
        bankAccountMap.put("ifscCode", bankAccount.getIFSC());
        return bankAccountMap;
    }

    public BankAccount bankAccountDeserializer(Map<String, String> bankAccountMap) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(bankAccountMap.get("id"));
        bankAccount.setUserId(bankAccountMap.get("userId"));
        bankAccount.setBankName(bankAccountMap.get("bankName"));
        bankAccount.setAccountNo(bankAccountMap.get("accountNumber"));
        bankAccount.setIFSC(bankAccountMap.get("ifscCode"));
        return bankAccount;
    }
    
}
