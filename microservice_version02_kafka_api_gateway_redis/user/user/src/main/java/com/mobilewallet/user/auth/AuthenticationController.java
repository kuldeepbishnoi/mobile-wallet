package com.mobilewallet.user.auth;

import java.security.Principal;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilewallet.user.entity.User;
import com.mobilewallet.user.entity.Wallet;
import com.mobilewallet.user.entity.Transaction;

import com.mobilewallet.user.service.userService.UserService;
import com.mobilewallet.user.kafka.KafkaListeneres;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@EnableCaching
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserService userService;
    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    private final KafkaListeneres kafkaListeneres;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody RegisterRequest request){
            return ResponseEntity.ok(service.register(request));
    };

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody AuthenticationRequest request){
            return ResponseEntity.ok(service.authenticate(request));
    };

    @GetMapping("/wallet")
    public ResponseEntity<Wallet> getWallet(Principal principal){
        String phoneNo = principal.getName();
        Wallet wallet = userService.findWalletByPhoneNo(phoneNo);
        return ResponseEntity.ok(wallet);
    }
    
    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(Principal principal){
        String phoneNo = principal.getName();
        Wallet wallet = userService.findWalletByPhoneNo(phoneNo);
        return ResponseEntity.ok(wallet.getBalance());
    }

    public ResponseEntity<Integer> getUser(Principal principal){
        String phoneNo = principal.getName();
        User user = userService.findByMobileNo(phoneNo);
        return ResponseEntity.ok(user.getId());
    }

    @PostMapping("/transaction")
    public ResponseEntity<Wallet> Transaction(@RequestBody Transaction transaction, Principal principal){
        String phoneNo = principal.getName();               
   
        // check for balance
        Wallet wallet = userService.findWalletByPhoneNo(phoneNo);
        if(wallet.getBalance() < transaction.getAmount()){
            return ResponseEntity.badRequest().build();
        }
        transaction.setFromWalletId(wallet.getId());
        //send transaction to transaction
        kafkaTemplate.send("transaction-topica", transaction);

        
        // if(new Date().getTime() - startTime >= 2000)
        while(kafkaListeneres.getUpdateWallet() == null){
            System.out.println("waiting for transaction to complete");
        }
        
        return ResponseEntity.ok(wallet);
        // return ResponseEntity.internalServerError().build();
    }
}
