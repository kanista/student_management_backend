package com.example.student.service;

import com.example.student.exception.StudentNotFoundException;
import com.example.student.model.StudentEntity;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @ExceptionHandler(StudentNotFoundException.class)

    @Override
    public StudentEntity saveStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    @Override
    public List<StudentEntity> getAllStudents(){
        return studentRepository.findAll();
    }

//    @Override
//    public Page<StudentEntity> getAllStudents(int page, int pageSize) {
//        Pageable pageable = PageRequest.of(page - 1, pageSize);
//        return studentRepository.findAll(pageable);
//    }

    @Override
    public Optional<StudentEntity> getStudentById(Integer studentId) {
        Optional<StudentEntity> student=studentRepository.findById(studentId);
        if(student.isPresent()){
            return student;
        }else{
            throw new StudentNotFoundException("Student not found with ID: "+studentId);
        }
    }

    @Override
    public void removeStudent(Integer studentId){
        if(studentRepository.existsById(studentId)){
            studentRepository.deleteById(studentId);
        }else {
            throw new StudentNotFoundException("Student not found with ID: "+studentId);
        }
    }

    @Override
    public StudentEntity updateStudent(StudentEntity newStudent, Integer studentId){
        Optional<StudentEntity> student=studentRepository.findById(studentId);
        if(student.isPresent()){
            StudentEntity existingStudent=student.get();
            existingStudent.setName(newStudent.getName());
            existingStudent.setAddress(newStudent.getAddress());
            return studentRepository.save(existingStudent);
        }else{
            throw new StudentNotFoundException("Student not found with ID: " + studentId);
        }
    }

}
