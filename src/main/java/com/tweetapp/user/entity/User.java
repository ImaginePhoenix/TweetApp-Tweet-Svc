package com.tweetapp.user.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RedisHash("user")
public class User {

	@NotBlank(message = "First Name cannot be empty !")
	private String firstName;

	@NotBlank(message = "Last Name cannot be empty !")
	private String lastName;

	@NotBlank(message = "Contact cannot be empty !")
	private String contactNo;

	@Id
	@NotBlank(message = "Email cannot be empty !")
	@Pattern(regexp = "[A-Za-z0-9.]+@[a-zA-z]+.com", message = "Invalid Email ID !")
	private String email;

	@NotBlank(message = "Password cannot be empty !")
	@Size(min = 8,message = "Password should be more than 8 characters !")
	private String password;

	@NotBlank(message = "Confirm password cannot be empty !")
	private String confirmPassword;

	private int status;

}
