package com.example.udc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.udc.dto.UserDto;
import com.example.udc.model.Resource;
import com.example.udc.model.User;
import com.example.udc.repository.ResourceRepository;
import com.example.udc.repository.UserRepository;
import com.example.udc.service.UserServiceImpl;

@Controller
public class AdminController {
	
	@Autowired
	private UserServiceImpl userService;

	 @GetMapping("/users")
	    public String userList(Model model) {
	        List<User> users = userService.findAll();
	        model.addAttribute("users", users);
	        return "userList";
	    }

	    @PostMapping("/updateUser")
	    public String updateUser(@RequestParam("username") String username,
	                             @RequestParam("role") String role,
	                             @RequestParam("fullName") String fullName) {
	        userService.updateUser(username, role, fullName);
	        return "redirect:/users";
	    }

	    @PostMapping("/deleteUser")
	    public String deleteUser(@RequestParam("username") String username) {
	        userService.deleteUser(username);
	        return "redirect:/users";
	    }
	 
}
