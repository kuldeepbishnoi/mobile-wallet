package com.mobilewallet.user.entity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "user")
public class User implements UserDetails{
    
	@Id
	@Column(name = "id")
    private String id;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z]{2,30}", message = "First name must contain only alphabets")
	@Column(name = "first_name")
	private String firstName;
	
	
	@NotNull
	@Pattern(regexp="[a-zA-Z]{2,30}", message = "Last name must contain only alphabets")
	@Column(name = "last_name")
	private String lastName;
	
    @NotNull
    @Column(name = "mobile_number")
	@Pattern(regexp="[6-9]{1}[0-9]{9}", message = "Mobile number must have 10 digits and should start with 6,7,8 or 9")
	private String mobileNo;
	
    @Email(message = "Email should be valid")
	@NotNull
    @Column(name = "email")
	private String email;

	@NotNull
	@Size(min=6,max=256, message = "Password must contain between 6 to 256 characters")
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	// no args constructor
	public User() {
		this.id = UUID.randomUUID().toString();
	}

	// all args constructor
	public User(String id, String firstName, String lastName, String mobileNo, String email, String password, Role role) {
		this.id = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return mobileNo;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
