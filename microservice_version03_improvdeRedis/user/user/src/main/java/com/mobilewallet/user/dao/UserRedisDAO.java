package com.mobilewallet.user.dao;

import com.mobilewallet.user.entity.User;
// import com.mobilewallet.user.entity.Wallet;

public interface UserRedisDAO {
    // C-Create
    public void save(User user);
    public User getUserByMobileNo(String mobileNo);
    public User getUserByEmail(String email);    
	// R-Read
	// public Task findById(int id);
	// // // U-Update
	// public void update(Task task);
	// // // D-Delete
	// public void deleteById(int id);
	// public User findUserByPhoneNo(String phoneNo);
}
