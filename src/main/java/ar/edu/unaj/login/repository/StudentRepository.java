package ar.edu.unaj.login.repository;

import ar.edu.unaj.login.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student,String> {
    List<Student> findStudentByFile(int file);
}
