package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class CrudController {
	@Autowired
	UserServices rs;
	@PostMapping("/register")

	public String getAllUser(@ModelAttribute Users user) {

		boolean userStatus = rs.emailExists(user.getEmail());
		if(userStatus == false) {
			rs.getAllUser(user);
			System.out.println("user added");
		}else {
			System.out.println("user already exit");
		}

		return "home";
	}
	@PostMapping("/validate")
	public String addvalidate(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session) {
		if(rs.validateUser(email,password) == true) {
			String role=rs.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "adminHome";
			}else {
				return "customerHome";
			}

		}else {
			return "login";
		}
	}
	@GetMapping("/pay")
	public String pay(@RequestParam String email) {
		boolean paymentStatus=false;
		if(paymentStatus==true) {
			Users user=rs.getUser(email);
			user.setPremium(true);
			rs.updateUser(user);
			}
		return "login";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}