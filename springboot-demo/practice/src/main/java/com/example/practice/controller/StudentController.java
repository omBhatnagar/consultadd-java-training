package com.example.practice.controller;

import com.example.practice.model.Student;
import com.example.practice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/allstudents")
    public ResponseEntity<?> getAllStudents(){
        Map<String, Object> studentMap = new LinkedHashMap<String, Object>();
        List<Student> students = this.studentService.getStudents();
        if(!students.isEmpty()){
            studentMap.put("status", 1);
            studentMap.put("data", students);
            return new ResponseEntity<>(studentMap, HttpStatus.OK);
        } else{
            studentMap.clear();
            studentMap.put("status", 0);
            studentMap.put("message", "No students found");
            return new ResponseEntity<>(studentMap, HttpStatus.NOT_FOUND);
            }
    }

    @PostMapping("/createstudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        Map<String, Object> response = this.studentService.createStudent(student);
        Object message = response.get("message");
        if(!(Boolean)response.get("status")){
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        // return new ResponseEntity<>(HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/deletestudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id){
        Map<String, Object> response = this.studentService.deleteStudent(Integer.parseInt(id));
        if(! (Boolean) response.get("status")){
            return new ResponseEntity<>(response.get("message"),HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(response.get("message"), HttpStatus.OK);
        }
    }

    //update
    @PutMapping("/updatestudent")
    public ResponseEntity<?> updateStudent(@RequestBody Student student){
        Map<String, Object> response = this.studentService.updateStudent(student);
        if(! (Boolean) response.get("status")){
            return new ResponseEntity<>(response.get("message"),HttpStatus.INTERNAL_SERVER_ERROR);
        } else{
            return new ResponseEntity<>(response.get("message"), HttpStatus.OK);
        }
    }

    //getbyid
    @GetMapping("/getstudentbyid")
    public ResponseEntity<?> getStudentById(@RequestParam String id){
        Optional<Student> student = this.studentService.getStudentById(Integer.parseInt(id));
        if(student.isPresent()){
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No student found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getstudentbyage/{age}")
    public ResponseEntity<?> getStudentByAge(@PathVariable Integer age){
        List<Student> students = this.studentService.getStudentByAge(age);
        if(students.size() < 1){
            return new ResponseEntity<>("No student found!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
    }
}
