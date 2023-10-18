package com.mobilewallet.user.service.walletService;

import com.mobilewallet.user.entity.Wallet;

public interface WalletService {
    public void save(Wallet wallet);
    public Wallet findById(String id);
    public Wallet findByUserId(String userId);
    public void update(Wallet wallet);
}
