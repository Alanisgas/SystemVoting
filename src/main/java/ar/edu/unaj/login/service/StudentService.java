package ar.edu.unaj.login.service;

import ar.edu.unaj.login.model.Student;
import ar.edu.unaj.login.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service()
public class StudentService {
    @Autowired
    private StudentRepository repository;

    //crud
    public Student addStudent(Student student){
        student.setStudentId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(student);
    }
    public List<Student> findAllStudent(){
        return repository.findAll();
    }
    public Optional<Student> getStudentById(String intId){
        return repository.findById(intId);
    }
    public List<Student> getFile(int file){
        return repository.findStudentByFile(file);
    }
    public String deleteStudent(String studentId){
        repository.deleteById(studentId);
        return  studentId+ "Student delete";
    }


}