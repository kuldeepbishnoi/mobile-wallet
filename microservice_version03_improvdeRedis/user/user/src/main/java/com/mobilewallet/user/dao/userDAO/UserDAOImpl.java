package com.mobilewallet.user.dao.userDAO;
import org.springframework.stereotype.Repository;

import com.mobilewallet.user.dao.UserRedisDAO;
import com.mobilewallet.user.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

    private final EntityManager entityManager;
    private final UserRedisDAO userRedisDAO;

    @Override
    public void save(User user) {
        System.out.println("XXXXXXXXXXXXX " + user.getMobileNo() + " saved in User DB " + " XXXXXXXXXXXXX");
        entityManager.persist(user);
        userRedisDAO.save(user);
    }

    @Override
    public User getUserByMobileNo(String mobileNo) {
        User redisUser = userRedisDAO.getUserByMobileNo(mobileNo);
        if (redisUser != null){
            return redisUser;
        }
        try{
            System.out.println("XXXXXXXXXXXXX " + mobileNo + " reterived from User DB" + " XXXXXXXXXXXXX");
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.mobileNo = :mobileNo", User.class).setParameter("mobileNo", mobileNo).getSingleResult();
            userRedisDAO.save(user);
            return user;
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    @Override
    public User getUserByEmail(String email) {
        User redisUser = userRedisDAO.getUserByEmail(email);
        if (redisUser != null){
            return redisUser;
        }
        
        try{
            System.out.println("XXXXXXXXXXXXX " + email + " reterived from User DB" + " XXXXXXXXXXXXX");
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class).setParameter("email", email).getSingleResult();
            userRedisDAO.save(user);
            return user;
        }
        catch(NoResultException e){
            return null;
        }
    }
}
