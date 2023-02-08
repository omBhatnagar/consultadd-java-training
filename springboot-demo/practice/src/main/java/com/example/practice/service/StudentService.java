package com.example.practice.service;

import com.example.practice.model.Student;
import com.example.practice.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    //Create New student details
    public Map<String, Object> createStudent(Student student){
        if(this.studentRepo.existsById(student.getId())){
            Map<String, Object> map = new HashMap<>();
            map.put("status", false);
            map.put("message", "Student already exists");
            return map;
        }
        else{
            this.studentRepo.save(student);
            Map<String, Object> map = new HashMap<>();
            map.put("status", true);
            map.put("message", "Student details are saved.");
            return map;
        }
    }

    //get all student details
    public List<Student> getStudents(){
        return this.studentRepo.findAll();

    }

    //delete student
    public Map<String, Object> deleteStudent(Integer id){
        Map<String, Object> map = new HashMap<>();
        try{
            if(this.studentRepo.existsById(id)){
                this.studentRepo.deleteById(id);
                map.put("status", true);
                map.put("message", "Student record deleted.");
                return map;
            }else {
                throw new Exception("Student does not exists!");
            }
        } catch(Exception e){
            map.put("status", false);
            map.put("message", e.getMessage());
            return map;
        }
    }

    //update student
    public Map<String, Object> updateStudent(Student student){
        Map<String, Object> map = new HashMap<>();
        try{
            if(this.studentRepo.existsById(student.getId())){
                this.studentRepo.save(student);
                map.put("status", true);
                map.put("message", "Updated successfully.");
                return map;
            } else{
                throw new Exception("Student not found!");
            }
        }catch(Exception e){
            map.put("status", false);
            map.put("message", e.getMessage());
            return map;
        }
    }

    //get student by id
    public Optional<Student> getStudentById(Integer id){
        return this.studentRepo.findById(id);
    }

}
