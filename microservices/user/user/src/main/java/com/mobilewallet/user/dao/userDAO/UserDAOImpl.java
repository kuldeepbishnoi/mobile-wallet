package com.mobilewallet.user.dao.userDAO;
import org.springframework.stereotype.Repository;

import com.mobilewallet.user.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Repository
public class UserDAOImpl implements UserDAO {

    // EntityManager
    private EntityManager entityManager;

    // Constructor injection
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Methods
    // C-Create
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserByMobileNo(String mobileNo) {
        try{
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.mobileNo = :mobileNo", User.class).setParameter("mobileNo", mobileNo).getSingleResult();
            return user;
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    @Override
    public User getUserByEmail(String email) {
        try{
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class).setParameter("email", email).getSingleResult();
            return user;
        }
        catch(NoResultException e){
            return null;
        }
    }
    // // R-Read
    // @Override
    // public Task findById(int id) {
    //     Task task = entityManager.find(Task.class, id);
    //     return task;
    // }

    // // U-Update
    // @Transactional
    // @Override
    // public void update(Task task) {
    //     entityManager.merge(task);
    // }
    // // D-Delete
    // @Transactional
    // @Override
    // public void deleteById(int id) {
    //     Task task = entityManager.find(Task.class, id);
    //     entityManager.remove(task);
    // }
}
