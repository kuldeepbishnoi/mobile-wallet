package com.mobilewallet.user.dao.userDAO;

import com.mobilewallet.user.entity.User;

public interface UserDAO {
    public void save(User user);
    public User getUserByMobileNo(String mobileNo);
    public User getUserByEmail(String email);    
}
