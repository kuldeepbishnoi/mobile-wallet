package com.mobilewallet.transaction.dao.walletDAO;

import com.mobilewallet.transaction.entity.Wallet;

public interface WalletDAO {
    // C-Create
    public void save(Wallet user);
    // // R-Read
    public Wallet findById(int id);
    // // U-Update
    public void update(Wallet task);
    public Wallet findByUserId(int userId);
    // // D-Delete
    // public void deleteById(int id);    
}
