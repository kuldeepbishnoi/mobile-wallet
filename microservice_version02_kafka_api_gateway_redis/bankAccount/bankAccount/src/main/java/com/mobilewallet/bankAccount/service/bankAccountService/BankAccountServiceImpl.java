package com.mobilewallet.bankAccount.service.bankAccountService;

import org.springframework.stereotype.Service;

import com.mobilewallet.bankAccount.dao.bankAccountDAO.BankAccountDAO;
import com.mobilewallet.bankAccount.entity.BankAccount;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService{

    private final BankAccountDAO bankAccountDAO;

    @Override
    @Transactional
    public void save(BankAccount bankAccount) {
        BankAccount oldAccount = bankAccountDAO.findByUserId(bankAccount.getUserId());
        if (oldAccount != null) {
            bankAccount.setId(oldAccount.getId());
            bankAccountDAO.update(bankAccount);
            return;
        }
        bankAccountDAO.save(bankAccount);
    }
    
}
