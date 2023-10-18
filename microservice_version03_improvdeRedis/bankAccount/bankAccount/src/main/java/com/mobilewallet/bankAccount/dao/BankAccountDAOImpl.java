package com.mobilewallet.bankAccount.dao;

import org.springframework.stereotype.Repository;

import com.mobilewallet.bankAccount.entity.BankAccount;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BankAccountDAOImpl implements BankAccountDAO {

    // EntityManager
    private final EntityManager entityManager;
    private final RedisAccountDAO redisAccountDAO;

    @Override
    @Transactional
    public void save(BankAccount bankAccount) {
        entityManager.persist(bankAccount);
        redisAccountDAO.save(bankAccount);
    }

    @Override
    @Transactional
    public void update(BankAccount bankAccount) {
        entityManager.merge(bankAccount);        
        redisAccountDAO.save(bankAccount);
    }

    @Override
    public BankAccount findByUserId(String userId) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>" + userId);
        BankAccount redisBankAccount = redisAccountDAO.findByUserId(userId);
        if (redisBankAccount != null){
            return redisBankAccount;
        }
        try{
            BankAccount bankAccount = entityManager.createQuery("SELECT u FROM BankAccount u WHERE u.userId = :userId", BankAccount.class)
                                                    .setParameter("userId", userId)
                                                    .getSingleResult();
            System.out.println("bankAccount: " + bankAccount);
            redisAccountDAO.save(bankAccount);
            return bankAccount;
        }
        catch(NoResultException e){
            return null;
        }
    }
}
