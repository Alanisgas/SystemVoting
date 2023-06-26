package ar.edu.unaj.login.service;
import ar.edu.unaj.login.service.util.EmailSenderExeption;
import org.springframework.security.crypto.password.PasswordEncoder;
import ar.edu.unaj.login.dto.EmailRequest;
import ar.edu.unaj.login.model.Student;
import ar.edu.unaj.login.repository.StudentRepository;
import com.sendgrid.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    EmailService emailService;


    //crud
    public Student addStudent(Student student)  throws Exception {

        // no manda mail por la api
        // Verificamos de que no exista algún usuario ya registrado.
        if (!isValid(student)) {
            throw new IllegalStateException("The email or the user is not valid. Try again.");
        }
        if (repository.findByEmail(student.getEmail()).isPresent()) {
            throw new IllegalStateException(String.format("Already exist an user with email %s", student.getEmail()));
        }
        // Encriptamos la contraseña.
        student.setPassword(student.getPassword());
        // Establecemos el TimeStamp de creación.
        student.setCreatedAt(System.currentTimeMillis() / 1000);
        // Establecemos el TimeStamp de modificación a la misma de creación.
        student.setCreatedAt(student.getCreatedAt());
        Student result = repository.save(student);

        if (!sendWelcomeMail(student)) {
            throw new Exception("The students was created, but the mail delivery failed.");
        }
        return result;
    }

    public Student updateStudent(Student student) {
        Student studentOriginal = repository.findById(student.getStudentId()).orElse(null);
        studentOriginal.setName(student.getName());
        studentOriginal.setType(student.getType());
        studentOriginal.setEmail(student.getEmail());
        studentOriginal.setPassword(student.getPassword());
        studentOriginal.setInstituto(student.getInstituto());
        return repository.save(studentOriginal);
    }

    public List<Student> findAllStudent() {
        return repository.findAll();
    }

    public Optional<Student> getStudentById(String intId) {
        return repository.findById(intId);
    }

    /* public List<Student> getFile(int file){
         return repository.findStudentByFile(file);
     }*/
    public String deleteStudent(String studentId) {
        repository.deleteById(studentId);
        return studentId + "Student delete";
    }
    public Student createUserWithoutSendMail(Student student) {
        // Verificamos de que no exista algún usuario ya registrado.
        if (!isValid(student)) {
            throw new IllegalStateException("The email or the user is not valid. Try again.");
        }
        if (repository.findByEmail(student.getEmail()).isPresent()) {
            throw new IllegalStateException(String.format("Already exist an user with email %s", student.getEmail()));
        }
        // Encriptamos la contraseña.
        student.setPassword(student.getPassword());
        // Establecemos el TimeStamp de creación.
        student.setCreatedAt(System.currentTimeMillis() / 1000);
        // Establecemos el TimeStamp de modificación a la misma de creación.
        student.setSetUpdatedAt(student.getCreatedAt());
        Student result = repository.save(student);
        return result;
    }
    public boolean isValid(Student student) {
        return student.getName() != null && student.getEmail() != null;
    }
    public boolean sendWelcomeMail(Student student) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(student.getEmail());
        emailRequest.setSubject("Contacto completado con exito.");
        emailRequest.setBody(String.format("Hola este es un mail de comprobacion   " + "", student.getName()));
        Response emailResponse = emailService.sendTextEmail(emailRequest);

        return emailResponse.getStatusCode() == 200 || emailResponse.getStatusCode() == 202;
    }


}