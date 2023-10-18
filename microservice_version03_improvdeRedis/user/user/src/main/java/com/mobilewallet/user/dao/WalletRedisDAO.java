package com.mobilewallet.user.dao;

import com.mobilewallet.user.entity.Wallet;

public interface WalletRedisDAO {
    // C-Create
    public void save(Wallet wallet);
    // // R-Read
    public Wallet findById(String id);
    // // U-Update
    public void update(Wallet wallet);
    public Wallet findByUserId(String userId);
}
