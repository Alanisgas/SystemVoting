package ar.edu.unaj.login.controller;

import ar.edu.unaj.login.model.Student;
import ar.edu.unaj.login.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/students")
@Api (value = "student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "create student",response = Iterable.class)
    @PostMapping(path = "/create-student")
    public ResponseEntity<Void> createStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return  new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping
    public ResponseEntity<Student> updateStudents(@RequestBody Student student){
        Student studentUpdate = studentService.updateStudent(student);
        return  new ResponseEntity(this.studentService.updateStudent(student),HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Student> getStudets(){
        return  studentService.findAllStudent();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("student/{studentId}")
    public Optional<Student> getStudentById(@PathVariable String studentId){
        return studentService.getStudentById(studentId);
    }/*
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("students/{file}")
    public List<Student> findStudenfile(@PathVariable int file){
        return  studentService.getFile(file);
    }*/
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("student/{studentIds}")
    public String deleteStudent(@PathVariable String studentIds){
        return  studentService.deleteStudent(studentIds);
    }
}
