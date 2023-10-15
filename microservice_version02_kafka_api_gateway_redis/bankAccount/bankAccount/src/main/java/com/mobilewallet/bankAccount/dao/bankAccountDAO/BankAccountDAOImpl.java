package com.mobilewallet.bankAccount.dao.bankAccountDAO;

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

    @Override
    @Transactional
    public void save(BankAccount bankAccount) {
        entityManager.persist(bankAccount);   
    }

    @Override
    @Transactional
    public void update(BankAccount bankAccount) {
        System.out.println("#################bankAccount merging: " + bankAccount);
        entityManager.merge(bankAccount);        
    }

    @Override
    public BankAccount findByUserId(Integer userId) {
        try{
            System.out.println("#################userId: " + userId);
            BankAccount bankAccount = entityManager.createQuery("SELECT u FROM BankAccount u WHERE u.userId = :userId", BankAccount.class)
                                                    .setParameter("userId", userId)
                                                    .getSingleResult();
            System.out.println("bankAccount: " + bankAccount);
            return bankAccount;
        }
        catch(NoResultException e){
            return null;
        }
    }
}
