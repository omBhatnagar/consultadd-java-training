package com.example.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.practice.model.Student;
import com.example.practice.repository.StudentRepo;
import com.example.practice.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTests {

    @Autowired
    private StudentService service;

    @MockBean
    private StudentRepo repo;

    @Test
    public void getStudentsTest(){
        when(repo.findAll()).thenReturn(Stream.of(new Student(1, "Om", 21, "A"), new Student(1, "Moe", 21, "A"), new Student(1, "Joe", 21, "A")).collect(Collectors.toList()));

        assertEquals(3, service.getStudents().size());
    }

    @Test
    public void getStudentByIdTest(){
        Integer id = 1;
        Student student = new Student(1, "Om", 21, "A");
        when(repo.findById(id)).thenReturn(Optional.of(student));
        assertEquals(true, service.getStudentById(1).isPresent());
    }

    @Test
    public void createStudentTest(){
        Student student = new Student(1, "Joe", 23, "B");
        when(repo.save(student)).thenReturn(student);
        assertEquals(true,(Boolean) service.createStudent(student).get("status"));
    }

    @Test
    public void updateStudentTest(){
        Student student = new Student(1, "Joe", 23, "B");
        when(repo.existsById(1)).thenReturn(true);
        when(repo.save(student)).thenReturn(student);
        assertEquals(true, (Boolean) service.updateStudent(student).get("status"));
    }

    @Test
    public void deleteStudentTest(){
        when(repo.existsById(1)).thenReturn(true);
        assertEquals(true, (Boolean) service.deleteStudent(1).get("status"));
    }

    @Test
    public void getStudentByAgeTest(){
        Integer age = 21;
        when(repo.findByAge(age)).thenReturn(Stream.of(new Student(1, "Om", 21, "A"), new Student(1, "Moe", 21, "A"), new Student(1, "Joe", 21, "A")).collect(Collectors.toList()));
        assertEquals(3, service.getStudentByAge(21).size());
    }
}
