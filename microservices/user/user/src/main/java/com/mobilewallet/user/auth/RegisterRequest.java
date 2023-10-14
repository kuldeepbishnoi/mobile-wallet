package com.mobilewallet.user.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String email;
	private String password;    
}
