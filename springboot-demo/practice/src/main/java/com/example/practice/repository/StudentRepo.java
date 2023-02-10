package com.example.practice.repository;

import com.example.practice.model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Integer> {
    List<Student> findByAge(Integer age);
}
