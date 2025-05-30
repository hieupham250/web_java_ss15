package com.data.controller;

import com.data.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {
    @GetMapping("students")
    public String showStudents(Model model) {
        List<Student> studentList = Arrays.asList(
                new Student("SV001", "Nguyen Van A", 20, "12A1", "a@example.com", "Hanoi", "0123456789"),
                new Student("SV002", "Tran Thi B", 21, "12A2", "b@example.com", "HCM", "0987654321"),
                new Student("SV003", "Le Van C", 19, "11B1", "c@example.com", "Danang", "0901234567")
        );

        model.addAttribute("students", studentList);
        return "studentlist";
    }
}
