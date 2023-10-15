package com.mobilewallet.user.service.userService;

import com.mobilewallet.user.entity.User;
import com.mobilewallet.user.entity.Wallet;

public interface UserService {
    // C-Create
	public void save(User user);
	public void signIn(String username, String password);
	public Wallet findWalletByPhoneNo(String phoneNo);
	public User findByMobileNo(String mobileNo);
    public User findByEmail(String email);
	public User findByMobileNoOrEmail(String username);
	// R-Read
	// public Task findById(int id);
	// // // U-Update
	// public void update(Task task);
	// // // D-Delete
	// public void deleteById(int id);
	// public User findUserByPhoneNo(String phoneNo);
}
