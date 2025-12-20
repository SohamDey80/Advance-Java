package com.cdac;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController 
{

	@GetMapping("/login")
	public String showform()
	{
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password,Model model)
	{
		

        if(username.equals("admin") && password.equals("admin123")) {
            model.addAttribute("user", username);
            return "welcome";
        } else {
            model.addAttribute("error", "Invalid Username or Password");
            return "error";
        }
		
		
		
	}
	
	
	
	
}
