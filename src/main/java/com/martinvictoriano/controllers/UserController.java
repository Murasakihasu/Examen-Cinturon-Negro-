package com.martinvictoriano.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.martinvictoriano.models.LoginUser;
import com.martinvictoriano.models.User;
import com.martinvictoriano.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	private final UserService userService;
	
	//Constructor
	public UserController(UserService userService){
		this.userService = userService;
		}
	
	//Show form for login and register
	@GetMapping({"/", "/login","/register"})
	public String loginAndRegister(	@ModelAttribute("user") User newUser,
									@ModelAttribute("userLogin") LoginUser loginUser) {
		return "login.jsp";
		}
		
	//Register data
	@PostMapping("/register")
	public String createNewUser(@Valid @ModelAttribute("user") User newUser,
								BindingResult validation,
								@ModelAttribute("userLogin") LoginUser loginUser,
			                       HttpSession sesion) {
		validation = userService.validateRegister(validation, newUser);
		if (validation.hasErrors()) {
			return "login.jsp";
			}
		User user = userService.createUser(newUser);
		sesion.setAttribute("user_id", user.getId());
		sesion.setAttribute("user_name", user.getName());
		return "redirect:/home/"+ user.getId();
		}

		
		
	//Login data
	@PostMapping("/login")
	public String loginUser(@Valid @ModelAttribute("userLogin") LoginUser loginUser,
							BindingResult validation,
							@ModelAttribute("user") User newUser,
							HttpSession sesion ) {
		validation = userService.validateLogin(validation, loginUser);
		if (validation.hasErrors()) {
			return "login.jsp";
			}
		else {
			User existingUser = userService.getUserByEmail(loginUser.getLoginEmail());
			sesion.setAttribute("user_id", existingUser.getId());
			sesion.setAttribute("user_name", existingUser.getName());
			return "redirect:/home/"+ existingUser.getId();
			}
		}
	
	//home
	@GetMapping("/home/{id}")
	public String home(@PathVariable("id") Long id, Model model, HttpSession sesion) {
		if(sesion.getAttribute("user_id") == null){
			return "redirect:";
		}
		model.addAttribute("user", userService.getUserById(id));
		//model.addAttribute("babies", babyService.allBabies());//
		return "home.jsp";
	}
	
	
	//Logout
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		sesion.invalidate();
		return "redirect:/";
	}
}
