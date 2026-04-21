package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // CREATE
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // READ
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") int id, @RequestBody Student updatedStudent) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setCourse(updatedStudent.getCourse());
            return studentRepository.save(student);
        }
        return null;
    }

    // DELETE ✅ (add this)
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        if (!studentRepository.existsById(id)) {
            return "Student not found";
        }
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") int id) {
        return studentRepository.findById(id).orElse(null);
    }
}