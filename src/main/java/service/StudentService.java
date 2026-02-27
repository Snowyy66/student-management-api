package com.example.studentapi.service;

import com.example.studentapi.model.Student;
import com.example.studentapi.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    private final List<Student> studentList;
    private final AtomicLong idGenerator;

    public StudentService() {
        this.studentList = new ArrayList<>();
        this.idGenerator = new AtomicLong(1);
    }

    // CREATE
    public Student addStudent(Student student) {
        student.setId(idGenerator.getAndIncrement());
        studentList.add(student);
        return student;
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return studentList;
    }

    // READ BY ID
    public Student getStudentById(Long id) {
        return studentList.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id: " + id)
                );
    }

    // UPDATE
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = getStudentById(id);
        // If not found → exception thrown automatically

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());

        return existingStudent;
    }

    // DELETE
    public void deleteStudent(Long id) {

        Student existingStudent = getStudentById(id);
        // If not found → exception thrown automatically

        studentList.remove(existingStudent);
    }
}