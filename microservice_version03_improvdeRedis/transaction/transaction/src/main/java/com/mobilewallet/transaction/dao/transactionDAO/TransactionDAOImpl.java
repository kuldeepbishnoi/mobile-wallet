package com.mobilewallet.transaction.dao.transactionDAO;

import org.springframework.stereotype.Repository;

import com.mobilewallet.transaction.entity.Transaction;

import jakarta.persistence.EntityManager;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

    // EntityManager
    EntityManager entityManager;

    // Constructor injection
    public TransactionDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Transaction transaction) {
        entityManager.persist(transaction);   
    }
}
