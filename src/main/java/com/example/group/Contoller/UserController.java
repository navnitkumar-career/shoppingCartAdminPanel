package com.example.group.Contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.example.group.Entity.User;
import com.example.group.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	public final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserRepository userrepository;

	@GetMapping
	public RedirectView dfl() {
		return new RedirectView("/user/userview");

	}

	@GetMapping("/userview")
	public String UserView(Model model) {
		List<User> userlist = userrepository.findAll();
		model.addAttribute("userlist", userlist);
		
		return "user-view.html";
	}

	@GetMapping("/add")
	public String add() {
		return "user-add.html";
	}

	@RequestMapping(value = "/add-save", method = RequestMethod.POST)
	// @PostMapping("/add-save")
	public RedirectView AddSave(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		userrepository.save(user);

		return new RedirectView("/user");
	}

	@GetMapping("/edit")
	public String edit(Model model, @RequestParam("id") long id) {
		User userlist = userrepository.findById(id).get();
		model.addAttribute("list", userlist);

		return "user-edit.html";
	}

	@PostMapping("/edit-save")
	public RedirectView EditSave(@RequestParam("id") long id, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("password") String password) {
		User user = userrepository.findById(id).get();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		userrepository.save(user);
		return new RedirectView("/user");
	}

	@GetMapping("/delete/{id}")
	@ResponseBody
	public RedirectView Delete(@PathVariable("id") long id) {

		userrepository.deleteById(id);
		return new RedirectView("/user");
	}

}
