package com.example.student.service;

import com.example.student.model.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public StudentEntity saveStudent(StudentEntity student);

    public List<StudentEntity> getAllStudents();

    public Optional<StudentEntity> getStudentById(Integer studentId);

    public void removeStudent(Integer studentId);

    public StudentEntity updateStudent(StudentEntity student, Integer studentId);
}
