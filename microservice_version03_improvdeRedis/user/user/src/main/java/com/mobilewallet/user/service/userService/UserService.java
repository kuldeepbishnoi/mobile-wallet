package com.mobilewallet.user.service.userService;

import com.mobilewallet.user.entity.User;
import com.mobilewallet.user.entity.Wallet;

public interface UserService {
	public void save(User user);
	public User findByMobileNo(String mobileNo);
    public User findByEmail(String email);
	public User findByMobileNoOrEmail(String username);
	// public void signIn(String username, String password);
	public Wallet findWalletByPhoneNo(String phoneNo);
}
