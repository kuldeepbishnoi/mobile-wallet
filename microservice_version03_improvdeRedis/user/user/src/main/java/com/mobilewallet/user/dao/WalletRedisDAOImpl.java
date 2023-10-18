package com.mobilewallet.user.dao;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mobilewallet.user.dao.walletDAO.WalletDAO;
import com.mobilewallet.user.entity.Wallet;
import com.mobilewallet.user.redis.RedisKeyGenerator;
import com.mobilewallet.user.redis.RedisSerializerAndDeserializer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import redis.clients.jedis.JedisPooled;

@Component
@RequiredArgsConstructor
public class WalletRedisDAOImpl implements WalletRedisDAO{

    private final RedisKeyGenerator redisKeyGenerator;
    private final JedisPooled jedis;
    private final RedisSerializerAndDeserializer rSAD;

    @Override
    public void save(Wallet wallet) {
        System.out.println(">>>>>>>>>>> " + wallet.getId() + " saved in Wallet Redis " + " <<<<<<<<<<<");
        jedis.hset(redisKeyGenerator.generateWalletIdKey(wallet.getId()), rSAD.walletSerializer(wallet));
        jedis.hset(redisKeyGenerator.generateUserIdKey(wallet.getUserId()), rSAD.walletSerializer(wallet));
    }

    @Override
    public Wallet findById(String id) {
        System.out.println(">>>>>>>>>>> " + id + " reterived from Wallet Redis " + " <<<<<<<<<<<");
        Map<String, String> walletMap = jedis.hgetAll(redisKeyGenerator.generateWalletIdKey(id));
        if (walletMap.isEmpty()){
            return null;
        }
        Wallet wallet = rSAD.walletDeserializer(walletMap);
        return wallet;
    }
    @Override
    public void update(Wallet wallet) {
        System.out.println(">>>>>>>>>>> " + wallet.getId() + " updated in Wallet Redis " + " <<<<<<<<<<<");
        jedis.hset(redisKeyGenerator.generateWalletIdKey(wallet.getId()), rSAD.walletSerializer(wallet));
        jedis.hset(redisKeyGenerator.generateUserIdKey(wallet.getUserId()), rSAD.walletSerializer(wallet));
    }

    @Override
    public Wallet findByUserId(String userId) {
        System.out.println(">>>>>>>>>>> " + userId + " reterived from Wallet Redis " + " <<<<<<<<<<<");
        Map<String, String> walletMap = jedis.hgetAll(redisKeyGenerator.generateUserIdKey(userId));
        if (walletMap.isEmpty()){
            return null;
        }
        Wallet wallet = rSAD.walletDeserializer(walletMap);
        return wallet;
    }

   
    
}
