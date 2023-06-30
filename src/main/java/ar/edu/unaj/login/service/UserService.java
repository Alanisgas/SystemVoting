package ar.edu.unaj.login.service;
import ar.edu.unaj.login.model.User;
import ar.edu.unaj.login.dto.EmailRequest;
import ar.edu.unaj.login.repository.UserRepository;
import com.sendgrid.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    EmailService emailService;

    private final VoteService voteService;
    @Autowired
    public UserService(VoteService voteService){
        this.voteService = voteService;
    }


    //crud
    public User addUser(User user)  throws Exception {

        // no manda mail por la api
        // Verificamos de que no exista algún usuario ya registrado.
        if (!isValid(user)) {
            throw new IllegalStateException("The email or the user is not valid. Try again.");
        }
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException(String.format("Already exist an user with email %s", user.getEmail()));
        }
        // Encriptamos la contraseña.
        user.setPassword(user.getPassword());
        // Establecemos el TimeStamp de creación.
        user.setCreatedAt(System.currentTimeMillis() / 1000);
        // Establecemos el TimeStamp de modificación a la misma de creación.
        user.setCreatedAt(user.getCreatedAt());
        User result = repository.save(user);

        if (!sendWelcomeMail(user)) {
            throw new Exception("The students was created, but the mail delivery failed.");
        }
        return result;
    }

    public User updateUser(User user) {
        User userOriginal = repository.findById(user.getUserId()).orElse(null);
        userOriginal.setName(user.getName());
        userOriginal.setType(user.getType());
        userOriginal.setEmail(user.getEmail());
        userOriginal.setPassword(user.getPassword());
        userOriginal.setInstituto(user.getInstituto());
        return repository.save(userOriginal);
    }
    public Optional<User> findUserByEmail(String email) {
        return repository.findByEmail(email);
    }
    public List<User> findAllUser() {
        return repository.findAll();
    }

    public Optional<User> getUserById(String intId) {
        return repository.findById(intId);
    }

    /* public List<User> getFile(int file){
         return repository.findUserByFile(file);
     }*/
    public String deleteUser(String UserId) {
        repository.deleteById(UserId);
        return UserId + "User delete";
    }
    public User createUserWithoutSendMail(User user) {
        // Verificamos de que no exista algún usuario ya registrado.
        if (!isValid(user)) {
            throw new IllegalStateException("The email or the user is not valid. Try again.");
        }
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException(String.format("Already exist an user with email %s", user.getEmail()));
        }
        // Encriptamos la contraseña.
        user.setPassword(user.getPassword());
        // Establecemos el TimeStamp de creación.
        user.setCreatedAt(System.currentTimeMillis() / 1000);
        // Establecemos el TimeStamp de modificación a la misma de creación.
        user.setSetUpdatedAt(user.getCreatedAt());
        User result = repository.save(user);
        return result;
    }
    public boolean isValid(User user) {
        return user.getName() != null && user.getEmail() != null;
    }
    public boolean sendWelcomeMail(User user) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(user.getEmail());
        emailRequest.setSubject("Contacto completado con exito.");
        emailRequest.setBody(String.format("Hola este es un mail de comprobacion   " + "", user.getName()));
        Response emailResponse = emailService.sendTextEmail(emailRequest);

        return emailResponse.getStatusCode() == 200 || emailResponse.getStatusCode() == 202;
    }


}
/*
@Service("userService")
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        //createRole();
    }

    private void createRole() {
        roleRepository.save(new Role("ADMIN"));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getUser());
        if(user == null){
            throw new SystemLoginException("");
        }else if(user.getPassword().equals(loginRequest.getPassword())){
            return new LoginResponse(user.getName(),user.getRoles());
        }
        throw new SystemLoginException("");
    }
}
 */