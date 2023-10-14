package com.mobilewallet.user.auth;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilewallet.user.entity.User;
import com.mobilewallet.user.entity.Wallet;
import com.mobilewallet.user.service.userService.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserService userService;
    
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

    @GetMapping("/user")
    public ResponseEntity<Integer> getUser(Principal principal){
        String phoneNo = principal.getName();
        User user = userService.findByMobileNo(phoneNo);
        return ResponseEntity.ok(user.getId());
    }
}
