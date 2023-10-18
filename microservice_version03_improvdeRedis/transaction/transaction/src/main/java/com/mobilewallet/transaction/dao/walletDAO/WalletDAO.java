package com.mobilewallet.transaction.dao.walletDAO;

import com.mobilewallet.transaction.entity.Wallet;

public interface WalletDAO {
    // C-Create
    public void save(Wallet user);
    // // R-Read
    public Wallet findById(String id);
    // // U-Update
    public void update(Wallet task);
    public Wallet findByUserId(String userId);
    // // D-Delete
    // public void deleteById(int id);    
}
