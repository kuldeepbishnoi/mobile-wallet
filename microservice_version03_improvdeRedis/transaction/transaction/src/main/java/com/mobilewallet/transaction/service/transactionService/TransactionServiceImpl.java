package com.mobilewallet.transaction.service.transactionService;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mobilewallet.transaction.dao.transactionDAO.TransactionDAO;
import com.mobilewallet.transaction.entity.Transaction;
import com.mobilewallet.transaction.entity.Wallet;
import com.mobilewallet.transaction.service.walletService.WalletService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionDAO transactionDAO;
    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    private final WalletService walletService;
    // public TransactionServiceImpl(WalletService walletService, TransactionDAO transactionDAO) {
    //     this.walletService = walletService;
    //     this.transactionDAO = transactionDAO;
    // }

    @Transactional
    @Override
    public void completeTransaction(Transaction transaction) {
                // check if wallets exists
        // Wallet fromWallet = walletService.findById(transaction.getFromWalletId());
        Wallet toWallet = walletService.findById(transaction.getToWalletId());
        // if(fromWallet == null || toWallet == null){
        //     throw new RuntimeException("Wallets do not exist");
        // }
        // if (fromWallet.getBalance() < transaction.getAmount()){
        //     throw new RuntimeException("Insufficient Balance");
        // }
        // fromWallet.setBalance(fromWallet.getBalance() - transaction.getAmount());
        
        toWallet.setBalance(toWallet.getBalance() + transaction.getAmount());
        // walletService.update(fromWallet);
        walletService.update(toWallet);
        transaction.setStatus("SUCCESSFULL");
        transactionDAO.save(transaction);   
        kafkaTemplate.send("tranasction-processed-topic", transaction);
    }
    
}
