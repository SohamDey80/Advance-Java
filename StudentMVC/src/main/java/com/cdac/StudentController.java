package com.cdac;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class StudentController {

	@GetMapping("/register")
    public String showForm() {
        return "register";
    }

	
	
	
	
    @PostMapping("/registerStudent")
    public String registerStudent( @ModelAttribute Student student,Model model)  
        
    {

       model.addAttribute("student",student);   

        return "view";
    }
}
