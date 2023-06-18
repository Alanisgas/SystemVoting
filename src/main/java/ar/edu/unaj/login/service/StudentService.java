package ar.edu.unaj.login.service;

import ar.edu.unaj.login.dto.PoliticalPartyDto;
import ar.edu.unaj.login.model.Student;
import ar.edu.unaj.login.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    //crud
    public Student addStudent(Student student){

        if (student.getType().toString() == "student"){
            student.setType("student");
            return repository.save(student);

        } else if (student.getType().toString() != "candidate") {
            student.setType("candidate");


            PoliticalPartyDto politicalPartyDto = new PoliticalPartyDto();
            politicalPartyDto.setNumberIDPP(politicalPartyDto.getNumberIDPP());
            politicalPartyDto.setNamePP(politicalPartyDto.getNamePP());
            return repository.save(student);
        }
        else {

            student.setType("student");
        student.setStudentId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(student);
        }
    }
    public Student updateStudent(Student student){
        Student studentOriginal = repository.findById(student.getStudentId()).orElse(null);
        studentOriginal.setName(student.getName());
        studentOriginal.setType(student.getType());
        studentOriginal.setEmail(student.getEmail());
        studentOriginal.setPassword(student.getPassword());
        studentOriginal.setInstituto(student.getInstituto());
        return repository.save(studentOriginal);
    }
    public List<Student> findAllStudent(){
        return repository.findAll();
    }
    public Optional<Student> getStudentById(String intId){
        return repository.findById(intId);
    }
   /* public List<Student> getFile(int file){
        return repository.findStudentByFile(file);
    }*/
    public String deleteStudent(String studentId){
        repository.deleteById(studentId);
        return  studentId+ "Student delete";
    }


}