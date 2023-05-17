package com.example.group.Contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.group.service.AuthService;

@Controller
@RequestMapping("/")
public class AuthController {
	public final Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	AuthService authService;

	@GetMapping
	public RedirectView dfl() {
		return new RedirectView("/login");
	}

	@GetMapping("/login")
	public String login() {
		return "login.html";
	}

	@PostMapping("/auth")
	public RedirectView auth(@RequestParam("email") String email, @RequestParam("password") String password) {
		Boolean flag = authService.auth(email, password);
		if (flag == false) {
			return new RedirectView("/login");
		} else {
			return new RedirectView("/home");
		}
	}

	@GetMapping("/home")
	public String home() {
		return "home.html";
	}

	@GetMapping("/register")
	public String loginRegister() {
		return "loginRegister.html";
	}

	@PostMapping("/userregister")
	public RedirectView userRegister(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "repeatpassword", required = false) String repeatpassword) {
		Boolean flag = authService.userRegister(email, password, name, repeatpassword);
		if (flag == true) {
			return new RedirectView("/login");
		} else {
			return new RedirectView("/register");
		}

	}

	@GetMapping("/forgot")
	public String forgotPasswordPage() {
		return "forgotPassword.html";
	}

	@PostMapping("/forgotpassword")
	public RedirectView forgotPassword(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "cpassword", required = false) String cpassword,
			@RequestParam(value = "npassword", required = false) String npassword,
			@RequestParam(value = "nrpassword", required = false) String nrpassword) {
		boolean flag = authService.forgotPassword(email, cpassword, npassword, nrpassword);
		if (flag == false) {
			return new RedirectView("/forgot");
		} else {
			return new RedirectView("/login");
		}
	}

	@GetMapping("/forgotPass")
	public String sendEmail() {
		return "sendEmail.html";
	}

	@PostMapping("/sendEmail")
	public RedirectView sendEmail(@RequestParam(value = "email", required = false) String email) {
		Boolean flag=authService.sendEmail(email);
		if(flag) {
			return new RedirectView("/login");
		}else {
			return new RedirectView("/forgotPass");
		}

	}

	@GetMapping("/email/{token}")
	public String email(@PathVariable("token") String token, Model model) {
		model.addAttribute("token", token);
		return "forgot.html";
	}

	@PostMapping("/changepass")
	public RedirectView changePassword(@RequestParam("token") String token,
			@RequestParam("repeatpassword") String repeatpassword, @RequestParam("password") String password) {
		authService.changePassword(token, repeatpassword, password);
		return new RedirectView("/login");
	}

}
