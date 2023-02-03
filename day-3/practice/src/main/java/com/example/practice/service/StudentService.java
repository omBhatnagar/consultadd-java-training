package com.example.practice.service;

import com.example.practice.model.Student;
import com.example.practice.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    //Create New student details
    public String createStudent(Student student){
        if(this.studentRepo.existsById(student.getId())){
            return "Student already exists";
        }
        else{
            this.studentRepo.save(student);
            return "Student details are saved.";
        }
    }

    //get all student details
    public List<Student> getStudents(){
        return this.studentRepo.findAll();

    }

    //delete student
    public String deleteStudent(Integer id){
        try{
            if(this.studentRepo.existsById(id)){
                this.studentRepo.deleteById(id);
                return "Student record deleted.";
            }else {
                throw new Exception("Student does not exists!");
            }
        } catch(Exception e){
            return "Error deleting student.";
        }
    }

    //update student
    public String updateStudent(Student student){
        try{
            if(this.studentRepo.existsById(student.getId())){
                this.studentRepo.save(student);
                return "Updated successfully.";
            } else{
                throw new Exception("Student not found!");
            }
        }catch(Exception e){
            return "Error updating!";
        }
    }

    //get student by id
    public Optional<Student> getStudentById(Integer id){
        return this.studentRepo.findById(id);
    }

}
