// package com.mobilewallet.user.controller;

// import org.springframework.boot.web.servlet.error.ErrorController;
// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Controller;
// import org.springframework.util.SystemPropertyUtils;
// // import org.springframework.util.ErrorHandler;
// import org.springframework.web.bind.annotation.RequestMapping;

// import jakarta.servlet.RequestDispatcher;
// import jakarta.servlet.http.HttpServletRequest;

// @Controller
// public class MyErrorController implements ErrorController  {

//     @RequestMapping("/error")
//     public String handleError(HttpServletRequest request) {
//         System.out.println(">>>>>>>>" + "Inside handleError" + "<<<<<<<<<<<");
//         Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
//         if (status != null) {
//             Integer statusCode = Integer.valueOf(status.toString());
        
//             if(statusCode == HttpStatus.NOT_FOUND.value()) {
//                 return "error-404.html";
//             }
//         }
//         return "error";
//     }
// }
