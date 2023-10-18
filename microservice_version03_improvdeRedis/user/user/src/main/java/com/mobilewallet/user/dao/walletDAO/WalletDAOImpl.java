package com.mobilewallet.user.dao.walletDAO;

import com.mobilewallet.user.dao.WalletRedisDAO;
import com.mobilewallet.user.entity.Wallet;

import org.springframework.stereotype.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WalletDAOImpl implements WalletDAO {

    // EntityManager
    private final EntityManager entityManager;
    private final WalletRedisDAO walletRedisDAO;
    // Constructor injection
    // public WalletDAOImpl(EntityManager entityManager) {
    //     this.entityManager = entityManager;
    // }

    // Methods
    // C-Create
    @Override
    public void save(Wallet wallet) {
        System.out.println("XXXXXXXXXXXXX " + wallet + " saved in Wallet DB " + "XXXXXXXXXXXXX");
        entityManager.persist(wallet);
        walletRedisDAO.save(wallet);
    }
    
    // R-Read
    @Override
    public Wallet findById(String id) {
        Wallet redisWallet = walletRedisDAO.findById(id);
        if(redisWallet != null){
            return redisWallet;
        }
        System.out.println("XXXXXXXXXXXXX " + id + " reterived from Wallet DB" + "XXXXXXXXXXXXX");
        Wallet wallet = entityManager.find(Wallet.class, id);
        walletRedisDAO.save(wallet);
        return wallet;
    }

    // U-Update
    @Override
    public void update(Wallet wallet) {
        System.out.println("XXXXXXXXXXXXX " + wallet.getUserId() + " updated in Wallet DB " + "XXXXXXXXXXXXX");
        entityManager.merge(wallet);
        walletRedisDAO.update(wallet);
        
    }

    @Override
    public Wallet findByUserId(String userId) {
        Wallet redisWallet = walletRedisDAO.findByUserId(userId);
        if(redisWallet != null){
            return redisWallet;
        }
        
        try{
            System.out.println("XXXXXXXXXXXXX " + userId + " reterived from Wallet DB" + "XXXXXXXXXXXXX");
            Wallet wallet = entityManager.createQuery("SELECT u FROM Wallet u WHERE u.userId = :userId", Wallet.class)
            .setParameter("userId", userId).getSingleResult();
            walletRedisDAO.save(wallet);
            return wallet;
        }
        catch(NoResultException e){
            return null;
        }

    }
}
