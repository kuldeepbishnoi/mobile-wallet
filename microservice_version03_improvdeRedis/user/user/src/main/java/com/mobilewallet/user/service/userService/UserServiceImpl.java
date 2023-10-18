package com.mobilewallet.user.service.userService;


import org.springframework.cache.annotation.Cacheable;
// import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.mobilewallet.user.dao.userDAO.UserDAO;
import com.mobilewallet.user.entity.User;
import com.mobilewallet.user.entity.Wallet;
import com.mobilewallet.user.redis.RedisKeyGenerator;
import com.mobilewallet.user.service.walletService.WalletService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
// import redis.clients.jedis.JedisPooled;

@Service
@RequiredArgsConstructor
// @EnableCaching
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;
	private final WalletService walletService;
	// private final UserRedisService userRedisService;
	private final RedisKeyGenerator redisKeyGenerator;


    // public UserServiceImpl(UserDAO userDAO, WalletService walletService) {
    //     this.userDAO = userDAO;
	// 	this.walletService = walletService;
    // }

	@Override
	@Transactional
	public void save(User user) {
		// check if phone no or email already exists
		if (userDAO.getUserByMobileNo(user.getMobileNo()) != null || userDAO.getUserByEmail(user.getEmail()) != null){
			throw new RuntimeException("User already exists");
		}
		// userRedisService.save(user);
		userDAO.save(user);

		// create wallet for user and give that to WalletService to save
		Wallet wallet = new Wallet();
		wallet.setUserId(user.getId());
		// wallet.setUserId(userDAO.getUserByMobileNo(user.getMobileNo()).getId());
		wallet.setBalance(1000);
		walletService.save(wallet);		
	}

	// @Override
	// public void signIn(String username, String password) {
	// 	if (username.contains("@")){
	// 		if (userDAO.getUserByEmail(username) == null){
	// 			throw new RuntimeException("User does not exist");
	// 		}
	// 		if (userDAO.getUserByEmail(username).getPassword() != password){
	// 			throw new RuntimeException("Incorrect password");
	// 		}
	// 	}
	// 	else{
	// 		if (userDAO.getUserByMobileNo(username) == null){
	// 			throw new RuntimeException("User does not exist");
	// 		}
	// 		if (userDAO.getUserByMobileNo(username).getPassword() != password){
	// 			throw new RuntimeException("Incorrect password");
	// 		}
	// 	}
	// }

	@Override
	public User findByMobileNo(String mobileNo){
		// userRedisService.get(redisKeyGenerator.generateMobileNoKey(mobileNo));
		// System.out.println(">>>>>>>> " + "findbyMobileNo called" + " <<<<<<<<");
		User user =  userDAO.getUserByMobileNo(mobileNo);
		return user;
	}

	@Override
    public User findByEmail(String email){
		// System.out.println(">>>>>>>> " + "findbyEmail called" + " <<<<<<<<");
		return userDAO.getUserByEmail(email);
	}

	@Override
	public User findByMobileNoOrEmail(String username) {
		// System.out.println(">>>>>>>> " + "findbyMobileNoOrEmail called" + " <<<<<<<<");
		if (username.contains("@")){
			return userDAO.getUserByEmail(username);
		}
		else{
			return userDAO.getUserByMobileNo(username);
		}
	}

	@Override
	public Wallet findWalletByPhoneNo(String phoneNo) {
		// System.out.println(">>>>>>>> " + "findWalletByPhoneNo called" + " <<<<<<<<");
		User user = findByMobileNo(phoneNo); 
		// System.out.println(">>>>>>>>>>>>>>" + user.getId());
		Wallet wallet = walletService.findByUserId(user.getId());
		// System.out.println(">>>>>>>>>>>>>>" + wallet.getId());
		return wallet;
	}
	
    // R-Read

    // @Override
	// public Task findById(int id) {
	// 	Task task = taskDAO.findById(id);
	// 	System.out.println("Task id is: " + task.getId());
	// 	return task;
	// }

	// // // U-Update
    // @Override
	// public void update(Task task) {
	// 	taskDAO.update(task);
	// 	System.out.println("Task updated: " + task.getId());
	// }

	// // // D-Delete
    // @Override
	// public void deleteById(int id) {
	// 	taskDAO.deleteById(id);
	// 	System.out.println("Task deleted: " + id);
	// }
}
