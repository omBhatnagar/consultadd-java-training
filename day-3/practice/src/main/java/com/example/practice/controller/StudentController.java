package com.example.practice.controller;

import com.example.practice.model.Student;
import com.example.practice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/allstudents")
    public List<Student> getAllStudents(){
        return this.studentService.getStudents();
    }

    @PostMapping("/createstudent")
    public String createStudent(@RequestBody Student student){
        return this.studentService.createStudent(student);
    }

    //delete
    @DeleteMapping("/deletestudent")
    public String deleteStudent(@RequestParam String id){
        return this.studentService.deleteStudent(Integer.parseInt(id));
    }

    //update
    @PutMapping("/updatestudent")
    public String updateStudent(@RequestBody Student student){
        return this.studentService.updateStudent(student);
    }

    //getbyid
    @GetMapping("/getstudentbyid")
    public Optional<Student> getStudentById(@RequestParam String id){
        return this.studentService.getStudentById(Integer.parseInt(id));
    }
}
