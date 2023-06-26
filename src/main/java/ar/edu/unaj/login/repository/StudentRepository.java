package ar.edu.unaj.login.repository;

import ar.edu.unaj.login.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student,String> {
    Optional<Student> findByEmail(String email);
}
