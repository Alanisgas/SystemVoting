package ar.edu.unaj.login.controller;

import ar.edu.unaj.login.model.Roles;
import ar.edu.unaj.login.model.User;
import ar.edu.unaj.login.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
@Api (value = "user")
public class UserController {

    @Autowired
    private UserService userService;
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "create User",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 406, message = "You are not able to create user")})
    @PostMapping(path = "/create-user")
    public ResponseEntity<Void> createUser(@RequestBody User user) throws Exception {
        //Optional<User> userExists = userService.findUserByEmail(user.getEmail());
        if (!isValidRole(user.getRoles())) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
       userService.addUser(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);/*
        if (userExists != null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            User newUser = userService.addUser(user);if (newUser != null) {
                // Enviamos el correo electrónico de bienvenida al nuevo usuario.
                if (userService.sendWelcomeMail(newUser)) {
                    return new ResponseEntity<Void>(HttpStatus.CREATED);
                } else {
                    // Si el envío de correo falla, puedes decidir cómo manejarlo.
                    return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);

            }
        }*/
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping
    public ResponseEntity<User> updateUsers(@RequestBody User user){
        User userUpdate = userService.updateUser(user);
        return  new ResponseEntity(this.userService.updateUser(user),HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<User> getStudets(){
        return  userService.findAllUser();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("User/{UserId}")
    public Optional<User> getUserById(@PathVariable String UserId){
        return userService.getUserById(UserId);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("User/{UserIds}")
    public String deleteUser(@PathVariable String UserIds){
        return  userService.deleteUser(UserIds);
    }
    private boolean isValidRole(Roles roles) {
        // Aquí puedes definir los roles válidos (por ejemplo, "votante" o "administrador")
        return roles != null && ("votante".equalsIgnoreCase(roles.getRoles()) || "administrador".equalsIgnoreCase(roles.getRoles()));
    }
}

