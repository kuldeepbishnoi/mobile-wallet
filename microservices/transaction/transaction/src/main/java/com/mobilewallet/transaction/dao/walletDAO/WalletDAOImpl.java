package com.mobilewallet.transaction.dao.walletDAO;

import com.mobilewallet.transaction.entity.Wallet;

import org.springframework.stereotype.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Repository
public class WalletDAOImpl implements WalletDAO {

    // EntityManager
    private EntityManager entityManager;

    // Constructor injection
    public WalletDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Methods
    // C-Create
    @Override
    public void save(Wallet user) {
        entityManager.persist(user);
    }
    
    // R-Read
    @Override
    public Wallet findById(int id) {
        Wallet wallet = entityManager.find(Wallet.class, id);
        return wallet;
    }

    // U-Update
    @Override
    public void update(Wallet wallet) {
        entityManager.merge(wallet);
    }
    // // D-Delete
    // @Transactional
    // @Override
    // public void deleteById(int id) {
    //     Task task = entityManager.find(Task.class, id);
    //     entityManager.remove(task);
    // }

    @Override
    public Wallet findByUserId(int userId) {
        System.out.println(">>>>>>>>>>>>>>" + userId);
        try{
            Wallet wallet = entityManager.createQuery("SELECT u FROM Wallet u WHERE u.userId = :userId", Wallet.class)
            .setParameter("userId", userId).getSingleResult();
            System.out.println(">>>>>>>>>>>>>>" + wallet);
            return wallet;
        }
        catch(NoResultException e){
            System.out.println(">>>>>>>>>>>>>>" + e);
            return null;
        }

    }
}
