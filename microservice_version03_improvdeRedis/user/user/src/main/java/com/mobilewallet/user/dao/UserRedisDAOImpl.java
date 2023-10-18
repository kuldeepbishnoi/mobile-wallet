package com.mobilewallet.user.dao;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
// import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.mobilewallet.user.dao.userDAO.UserDAO;
import com.mobilewallet.user.entity.Role;
import com.mobilewallet.user.entity.User;
import com.mobilewallet.user.entity.Wallet;
import com.mobilewallet.user.redis.RedisKeyGenerator;
import com.mobilewallet.user.redis.RedisSerializerAndDeserializer;
import com.mobilewallet.user.service.walletService.WalletService;

import java.util.HashMap;
import java.util.Map;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import redis.clients.jedis.JedisPooled;

@Component
@RequiredArgsConstructor
public class UserRedisDAOImpl implements UserRedisDAO{
	private final JedisPooled jedis;
	private final RedisKeyGenerator redisKeyGenerator;
	private final RedisSerializerAndDeserializer rSAD;
	
	@Override
	public void save(User user) {
		System.out.println(">>>>>>>>>>> " + user.getMobileNo() + " saved in User Redis " + " <<<<<<<<<<<");
		Map<String, String> userMap = rSAD.userSerializer(user);
		jedis.hset(redisKeyGenerator.generateMobileNoKey(user.getMobileNo()), userMap);
		jedis.hset(redisKeyGenerator.generateEmailKey(user.getEmail()), userMap);
	}

	@Override
	public User getUserByMobileNo(String mobileNo) {
		System.out.println(">>>>>>>>>>> " + mobileNo + " reterived from User Redis " + " <<<<<<<<<<<");
		Map<String, String> userMap = jedis.hgetAll(redisKeyGenerator.generateMobileNoKey(mobileNo));
		if (userMap.isEmpty()){
			return null;
		}
		User user = rSAD.userDeserializer(userMap);
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		System.out.println(">>>>>>>>>>> " + email + " reterived from User Redis " + " <<<<<<<<<<<");
		Map<String, String> userMap = jedis.hgetAll(redisKeyGenerator.generateEmailKey(email));
		if (userMap.isEmpty()){
			return null;
		}
		User user = rSAD.userDeserializer(userMap);
		return user;
	}


}
