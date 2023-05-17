package com.example.group.service;

public interface AuthService {

	Boolean auth(String email, String password);

	Boolean userRegister(String email, String password, String name, String repeatpassword);

	boolean forgotPassword(String email, String cpassword, String npassword, String nrpassword);

	Boolean sendEmail(String email);

	void changePassword(String token, String repeatpassword, String password);

}
