package com.cdac;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/register")
    public String showForm() {
        return "register";
    }

    @PostMapping("/registerStudent")
    public String register(@ModelAttribute Student student) {
        studentDAO.registerStudent(student);
        return "redirect:/viewStudents";
    }

    @GetMapping("/viewStudents")
    public String viewStudents(Model model) {
        List<Student> students = studentDAO.viewAllStudents();
        model.addAttribute("students", students);
        return "viewStudents";
    }

    // --- NEW HANDLERS ---

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable("id") int id, Model model) { // Added ("id")
        Student student = studentDAO.getStudentById(id);
        model.addAttribute("student", student);
        return "editStudent";
    }

    @PostMapping("/updateStudent")
    public String update(@ModelAttribute Student student, @RequestParam("oldId") int oldId) {
        studentDAO.updateStudent(student, oldId);
        return "redirect:/viewStudents";
    }

    @GetMapping("/deleteStudent/{id}")
    public String delete(@PathVariable("id") int id) { // Added ("id")
        studentDAO.deleteStudent(id);
        return "redirect:/viewStudents";
    }
}