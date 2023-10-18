// package com.mobilewallet.bankAccount.rest;


// import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestHeader;


// // @FeignClient(name="mobile-wallet-user", url = "http://mobile-wallet-user:8000")
// @FeignClient(name="mobile-wallet-user", url = "${MOBILEWALLET_USER_SERVICE_HOST:http://localhost}:8000")
// public interface UserClient {
//     @GetMapping("api/v1/user")
//     public Integer getUser(@RequestHeader("Authorization") String token);
// }