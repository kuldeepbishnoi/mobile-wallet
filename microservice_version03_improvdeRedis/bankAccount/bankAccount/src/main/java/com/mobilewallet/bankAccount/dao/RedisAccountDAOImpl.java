package com.mobilewallet.bankAccount.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.mobilewallet.bankAccount.entity.BankAccount;
import com.mobilewallet.bankAccount.redis.RedisKeyGenerator;
import com.mobilewallet.bankAccount.redis.RedisSerializerAndDeserializer;

import lombok.RequiredArgsConstructor;
import redis.clients.jedis.JedisPooled;


@Component
@RequiredArgsConstructor    
public class RedisAccountDAOImpl implements RedisAccountDAO{

    private final RedisKeyGenerator redisKeyGenerator;
    private final RedisSerializerAndDeserializer rSAD;
    private final JedisPooled jedis;

    @Override
    public void save(BankAccount bankAccount) {
        System.out.println(">>>>>>>>>>> " + bankAccount + " saved in BankAccount Redis " + " <<<<<<<<<<<");
		Map<String, String> bankAccountMap = rSAD.bankAccountSerializer(bankAccount);
		jedis.hset(redisKeyGenerator.generateUserIdKey(bankAccount.getUserId()), bankAccountMap);   
    }

    @Override
    public BankAccount findByUserId(String userId) {
        
        Map<String, String> bankAccountMap = jedis.hgetAll(redisKeyGenerator.generateUserIdKey(userId));
        if (bankAccountMap.isEmpty()){
            return null;
        }
        System.out.println(">>>>>>>>>>> " + userId + " reterived from BankAccount Redis " + " <<<<<<<<<<<");
        BankAccount bankAccount = rSAD.bankAccountDeserializer(bankAccountMap);
        return bankAccount;
    }
    
}
