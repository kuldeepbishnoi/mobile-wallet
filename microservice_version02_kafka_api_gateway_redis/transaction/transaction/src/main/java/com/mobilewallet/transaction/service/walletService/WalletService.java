package com.mobilewallet.transaction.service.walletService;

import com.mobilewallet.transaction.entity.Wallet;

public interface WalletService {
    public void save(Wallet wallet);
    public Wallet findById(int id);
    public Wallet findByUserId(int userId);
    public void update(Wallet wallet);
}
