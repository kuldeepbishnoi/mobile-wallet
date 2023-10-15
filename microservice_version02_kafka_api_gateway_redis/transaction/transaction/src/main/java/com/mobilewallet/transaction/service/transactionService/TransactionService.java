package com.mobilewallet.transaction.service.transactionService;

import com.mobilewallet.transaction.entity.Transaction;

public interface TransactionService {
    public void completeTransaction(Transaction transaction);
}
