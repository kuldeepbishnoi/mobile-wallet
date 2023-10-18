// package com.mobilewallet.bankAccount.rest;


// import org.springframework.core.env.Environment;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.mobilewallet.bankAccount.entity.BankAccount;
// // import com.mobilewallet.transaction.service.userService.UserService;
// import com.mobilewallet.bankAccount.service.bankAccountService.BankAccountService;

// import lombok.RequiredArgsConstructor;

// @RestController
// @RequiredArgsConstructor
// @RequestMapping("/api/v1/")
// public class BankAccountController {

//     // private final UserService userService;
//     private final UserClient userClient;
//     private final Environment environment;
//     private final BankAccountService bankAccountService;
    
//     @PostMapping("/account")
//     public ResponseEntity<BankAccount> saveAccount(@RequestBody BankAccount bankAccount, @RequestHeader("Authorization") String token){
//         String hostis = environment.getProperty("HOSTNAME");
//         System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>****************** port is " + hostis);
//         String x = environment.getProperty("MOBILEWALLET_USER_SERVICE_HOST");
//         System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>****************** MOBILEWALLET_USER_SERVICE_HOST is " + x);

//         Integer  userId = userClient.getUser(token);
//         System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>******************" + userId);
//         bankAccount.setUserId(userId);
//         bankAccountService.save(bankAccount);
        
//         return ResponseEntity.ok(bankAccount);
//     }
// }
