package com.mobilewallet.user.controller;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilewallet.user.entity.User;
import com.mobilewallet.user.entity.Wallet;
import com.mobilewallet.user.entity.BankAccount;
import com.mobilewallet.user.entity.Transaction;
import com.mobilewallet.user.service.AuthenticationService;
import com.mobilewallet.user.service.userService.UserService;
import com.mobilewallet.user.service.walletService.WalletService;
import com.mobilewallet.user.kafka.KafkaListeneres;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
// @EnableCaching
public class UserRestController {

    private final AuthenticationService service;
    private final UserService userService;
    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    private final KafkaTemplate<String, BankAccount> bankKafkaTemplate;
    private final WalletService walletService;
    private final KafkaListeneres kafkaMessageHandler;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody RegisterRequest request){
            return ResponseEntity.ok(service.register(request));
    };

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody LoginRequest request){
            return ResponseEntity.ok(service.authenticate(request));
    };

    @GetMapping("/wallet")
    public ResponseEntity<Wallet> getWallet(Principal principal){
        String phoneNo = principal.getName();
        Wallet wallet = userService.findWalletByPhoneNo(phoneNo);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(Principal principal){
        String phoneNo = principal.getName();
        User user = userService.findByMobileNo(phoneNo);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/balance")
    public ResponseEntity<String> getBalance(Principal principal){
        System.out.println("principal name is " + principal.getName());
        String phoneNo = principal.getName();
        Wallet wallet = userService.findWalletByPhoneNo(phoneNo);
        return ResponseEntity.ok(phoneNo + " balance is " + wallet.getBalance());
        // return ResponseEntity.ok(4);
    }
    
    @PostMapping("/bankaccount")
    public ResponseEntity<String> bankAccount(@RequestBody BankAccount bankAccount, Principal principal){
        System.out.println("principal name is " + principal.getName());
        String phoneNo = principal.getName();
        User user = userService.findByMobileNo(phoneNo);
        bankAccount.setUserId(user.getId());
        bankAccount.setAccountNo(phoneNo);
        bankKafkaTemplate.send("bankaccount-process-topic", bankAccount);  
        return ResponseEntity.ok("Bank Account Information Successfully Processed");

        // return ResponseEntity.ok(4);
    }

    @PostMapping("/transaction")
    public ResponseEntity<String> Transaction(@RequestBody Transaction transaction, Principal principal){
        String phoneNo = principal.getName();               
   
        // check for balance
        Wallet wallet = userService.findWalletByPhoneNo(phoneNo);
        if(wallet.getBalance() < transaction.getAmount()){ 
            return new ResponseEntity<>("insufficient balance", HttpStatus.BAD_REQUEST);
        }
        transaction.setFromWalletId(wallet.getId());
        transaction.setStatus("locked");

        //block the amount
        wallet.setBalance(wallet.getBalance() - transaction.getAmount());
        walletService.update(wallet);
        

        //send transaction to transaction
        kafkaTemplate.send("tranasction-process-topic", transaction);


        // Retrieve the transaction status asynchronously
        CompletableFuture<Transaction> future = kafkaMessageHandler.transactionStatus();

        try {
            Transaction retrievedTransaction = future.get();
            if (retrievedTransaction != null) {
                   return ResponseEntity.ok("Transaction status: " + retrievedTransaction.toString());
            } else {
                wallet.setBalance(wallet.getBalance() + transaction.getAmount());
                walletService.update(wallet);
                return new ResponseEntity<>("Transaction Unsuccesfull - amount unblocked", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // Handle exceptions if necessary
            return ResponseEntity.status(500).body("Error retrieving transaction status: " + e.getMessage());
        }
        
        // if(new Date().getTime() - startTime >= 2000)
        // while(kafkaListeneres.getUpdateWallet() == null){
        //     System.out.println("waiting for transaction to complete");
        // }
        
        // return new ResponseEntity<>(
        // transaction.getAmount() + " Rs Amount is locked & updated balance is " + wallet.getBalance(),
        //   HttpStatus.OK);
        // return ResponseEntity.internalServerError().build();
    }
}
