package com.example.studentapi.controller;

import com.example.studentapi.model.Student;
import com.example.studentapi.payload.ApiResponse;
import com.example.studentapi.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(
            @Valid @RequestBody Student student) {

        Student createdStudent = studentService.addStudent(student);

        ApiResponse<Student> response =
                new ApiResponse<>(true, "Student created successfully", createdStudent);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {

        List<Student> students = studentService.getAllStudents();

        ApiResponse<List<Student>> response =
                new ApiResponse<>(true, "Students fetched successfully", students);

        return ResponseEntity.ok(response);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable Long id) {

        Student student = studentService.getStudentById(id);

        ApiResponse<Student> response =
                new ApiResponse<>(true, "Student fetched successfully", student);

        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {

        Student updated = studentService.updateStudent(id, student);

        ApiResponse<Student> response =
                new ApiResponse<>(true, "Student updated successfully", updated);

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();  // 204 NO CONTENT
    }
}