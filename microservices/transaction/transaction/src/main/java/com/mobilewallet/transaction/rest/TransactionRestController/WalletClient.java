package com.mobilewallet.transaction.rest.TransactionRestController;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mobilewallet.transaction.entity.Wallet;

// @FeignClient(name="mobile-wallet-user", url = "http://mobile-wallet-user:8000")
@FeignClient(name="mobile-wallet-user", url = "${MOBILEWALLET_USER_SERVICE_HOST:http://localhost}:8000")
public interface WalletClient {
    @GetMapping("api/v1/wallet")
    public Wallet getWallet(@RequestHeader("Authorization") String token);
}