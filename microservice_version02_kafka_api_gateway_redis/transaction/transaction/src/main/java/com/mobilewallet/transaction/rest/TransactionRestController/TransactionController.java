package com.mobilewallet.transaction.rest.TransactionRestController;


import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilewallet.transaction.entity.Transaction;
import com.mobilewallet.transaction.entity.Wallet;
import com.mobilewallet.transaction.service.transactionService.TransactionService;
// import com.mobilewallet.transaction.service.userService.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class TransactionController {

    // private final UserService userService;
    private final TransactionService transactionService;
    private final WalletClient walletClient;
    private final Environment environment;
    // private final KafkaTemplate<String, String> kafkaTemplate;

    
    @PostMapping("/transaction")
    public ResponseEntity<String> completeTransaction(@Valid @RequestBody Transaction transaction, @RequestHeader("Authorization") String token){
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>******************" + token + " " + transaction.getAmount());
       
        // String hostis = environment.getProperty("HOSTNAME");
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>****************** pod is " + hostis);
        // String x = environment.getProperty("MOBILEWALLET_USER_SERVICE_HOST");
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>****************** MOBILEWALLET_USER_SERVICE_HOST is " + x);

        // Wallet wallet = walletClient.getWallet(token);
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>******************" + wallet.getId());
        // transaction.setFromWalletId(wallet.getId());
        // transactionService.completeTransaction(transaction);
        // wallet.setBalance(wallet.getBalance() - transaction.getAmount());
        return ResponseEntity.ok("hi");
    }

    // @PostMapping("/message")
    // public void Message(){
    //     kafkaTemplate.send("transaction-topic", "transaction controller topic");
    // }
}
