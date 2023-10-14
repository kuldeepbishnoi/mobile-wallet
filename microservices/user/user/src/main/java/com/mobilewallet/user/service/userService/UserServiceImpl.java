package com.mobilewallet.user.service.userService;


import org.springframework.stereotype.Service;

import com.mobilewallet.user.dao.userDAO.UserDAO;
import com.mobilewallet.user.entity.User;
import com.mobilewallet.user.entity.Wallet;
import com.mobilewallet.user.service.walletService.WalletService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;
	private WalletService walletService;
	
    public UserServiceImpl(UserDAO userDAO, WalletService walletService) {
        this.userDAO = userDAO;
		this.walletService = walletService;
    }

	@Override
	@Transactional
	public void save(User user) {
		// check if phone no or email already exists
		if (userDAO.getUserByMobileNo(user.getMobileNo()) != null || userDAO.getUserByEmail(user.getEmail()) != null){
			throw new RuntimeException("User already exists");
		}
		userDAO.save(user);
		Wallet wallet = new Wallet();
		wallet.setUserId(userDAO.getUserByMobileNo(user.getMobileNo()).getId());
		wallet.setBalance(0);
		walletService.save(wallet);
		
	}

	@Override
	public void signIn(String username, String password) {
		if (username.contains("@")){
			if (userDAO.getUserByEmail(username) == null){
				throw new RuntimeException("User does not exist");
			}
			if (userDAO.getUserByEmail(username).getPassword() != password){
				throw new RuntimeException("Incorrect password");
			}
		}
		else{
			if (userDAO.getUserByMobileNo(username) == null){
				throw new RuntimeException("User does not exist");
			}
			if (userDAO.getUserByMobileNo(username).getPassword() != password){
				throw new RuntimeException("Incorrect password");
			}
		}
	}

	@Override
	public User findByMobileNo(String mobileNo){
		return userDAO.getUserByMobileNo(mobileNo);
	}
	@Override
    public User findByEmail(String email){
		return userDAO.getUserByEmail(email);
	}

	@Override
	public User findByMobileNoOrEmail(String username) {
		if (username.contains("@")){
			return userDAO.getUserByEmail(username);
		}
		else{
			return userDAO.getUserByMobileNo(username);
		}
	}

	@Override
	public Wallet findWalletByPhoneNo(String phoneNo) {
		User user = findByMobileNo(phoneNo); 
		System.out.println(">>>>>>>>>>>>>>" + user.getId());
		Wallet wallet = walletService.findByUserId(user.getId());
		System.out.println(">>>>>>>>>>>>>>" + wallet.getId());
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
