package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class UserController {
 @Autowired
 UserService userservice;




    @PostMapping("/student")
    public User register(@Valid @RequestBody User user) {
        User existingUser = userservice.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("Username already exists");
        }
        return userservice.createUser(user);
    }


    @PostMapping("/login")
    public User userLogin(@RequestParam String username, @RequestParam String password){
        User user = userservice.findByUsername(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(user == null){
            throw new RuntimeException("there is not existing user ");
        }

        String userPassword = user.getPassword();
        String dbPassword = password;


        if(passwordEncoder.matches(dbPassword,userPassword)) {
            return user;
        }else{

            throw new RuntimeException("user name and password are incorrect");
        }
    }

    @PutMapping("/student/{id}")
    public User updateUser(@PathVariable Long id , @RequestBody User updatedUser){
    User user = userservice.findById(id);
    if(user==null){
        throw new RuntimeException("user not found by id" + id);
    }
    user.setUsername(updatedUser.getUsername());
    user.setEmail(updatedUser.getEmail());
    user.setPassword(updatedUser.getPassword());
    user.setCountry(updatedUser.getCountry());
    return userservice.createUser(user);
    }

    @GetMapping("/student/{id}")
    public User viewUser(@PathVariable Long id){
        User user = userservice.findById(id);
        if(user == null){
            throw new RuntimeException("user not  by id " + id);
        }
        return user;
    }

    @GetMapping("/student")
    public List<User> viewAllUsers(){
        return userservice.viewAllUsers();
    }

    @DeleteMapping("/student/{id}")
    public User deleteUser(@PathVariable Long id){
        User user = userservice.findById(id);
        if(user == null){
            throw new RuntimeException("There is no user with id " + id);
        }
        userservice.deleteUser(id);
        return user;
    }
}

