package com.mobilewallet.user.dao.userDAO;

import com.mobilewallet.user.entity.User;

public interface UserDAO {
    // C-Create
    public void save(User user);
    // // R-Read
    public User getUserByMobileNo(String mobileNo);
    public User getUserByEmail(String email);
    // // U-Update
    // public void update(Task task);
    // // D-Delete
    // public void deleteById(int id);    
}
