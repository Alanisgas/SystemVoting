package ar.edu.unaj.login.controller;

import ar.edu.unaj.login.model.PoliticalParty;
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
@RequestMapping(value = "/Users")
@Api (value = "User")
public class UserController {

    @Autowired
    private UserService userService;
    @CrossOrigin(origins = "http://localhost:8080")
    @ApiOperation(value = "create User",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 406, message = "You are not able to create user")})
    @PostMapping(path = "/create-User")
    public ResponseEntity<Void> createUser(@RequestBody User user) throws Exception {
        Optional<User> userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            userService.addUser(user);
            return  new ResponseEntity<Void>(HttpStatus.CREATED);
        }
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping
    public ResponseEntity<User> updateUsers(@RequestBody User user){
        User userUpdate = userService.updateUser(user);
        return  new ResponseEntity(this.userService.updateUser(user),HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public List<User> getStudets(){
        return  userService.findAllUser();
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("User/{UserId}")
    public Optional<User> getUserById(@PathVariable String UserId){
        return userService.getUserById(UserId);
    }/*
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("Users/{file}")
    public List<User> findStudenfile(@PathVariable int file){
        return  UserService.getFile(file);
    }*/
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("User/{UserIds}")
    public String deleteUser(@PathVariable String UserIds){
        return  userService.deleteUser(UserIds);
    }
}
