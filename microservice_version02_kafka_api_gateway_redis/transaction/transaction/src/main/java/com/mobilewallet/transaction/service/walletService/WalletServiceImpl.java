package com.mobilewallet.transaction.service.walletService;

import org.springframework.stereotype.Service;

import com.mobilewallet.transaction.dao.walletDAO.WalletDAO;
import com.mobilewallet.transaction.entity.Wallet;

import jakarta.transaction.Transactional;

@Service
public class WalletServiceImpl implements WalletService{

    WalletDAO walletDAO;
    public WalletServiceImpl(WalletDAO walletDAO) {
        this.walletDAO = walletDAO;
    }

    @Override
    @Transactional
    public void save(Wallet wallet) {
        walletDAO.save(wallet);    
    }

    @Override
    public Wallet findByUserId(int userId) {
        return walletDAO.findByUserId(userId);
    }

    @Override
    public Wallet findById(int id) {
        return walletDAO.findById(id);
    }

    @Override
    @Transactional
    public void update(Wallet wallet) {
        walletDAO.update(wallet);
    }
    
}
