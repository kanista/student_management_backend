package com.example.student.controller;

import com.example.student.exception.StudentNotFoundException;
import com.example.student.model.StudentEntity;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ExceptionHandler(StudentNotFoundException.class)

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody StudentEntity student){
        StudentEntity studentAdd= studentService.saveStudent(student);
        System.out.println(studentAdd);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student saved successfully");
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentEntity>> getAllStudents(){
        List<StudentEntity> studentEntities=studentService.getAllStudents();
        System.out.println(studentEntities);
        return ResponseEntity.status(HttpStatus.OK).body(studentEntities);
    }


    @GetMapping("/getStudent/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Integer id) {
        try {
            Optional<StudentEntity> student = studentService.getStudentById(id);
            return ResponseEntity.ok(student);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.ofNullable(e.getMessage()));
        }
    }


    @DeleteMapping("removeStudent/{id}")
    public ResponseEntity<String> removeStudent(@PathVariable Integer id){
        try{
             studentService.removeStudent(id);
             return ResponseEntity.ok("Student removed successfully");
        }  catch (StudentNotFoundException e){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("updateStudent/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody StudentEntity student, @PathVariable Integer id){
        try{
            StudentEntity updatedStudent= studentService.updateStudent(student,id);
            System.out.println(updatedStudent);
            return ResponseEntity.ok("Successfully Updated");
        }catch (StudentNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
