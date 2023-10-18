// package com.mobilewallet.transaction.dao.transactionDAO;
package com.mobilewallet.bankAccount.dao;

import com.mobilewallet.bankAccount.entity.BankAccount;

public interface BankAccountDAO {
    public void save(BankAccount transaction);

    public void update(BankAccount bankAccount);

    public BankAccount findByUserId(String userId);    

}
