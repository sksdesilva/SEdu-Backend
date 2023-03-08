package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userrepository;

    public User createUser(User user) {
        return userrepository.save(user);
    }

//    public boolean userLogin(String email, String password)  {
//        User user = userrepository.findByUsername(email);
//        if(user.getPassword() == password){
//            return true;
//    }else
//        return false;
//    }

    public List<User> viewAllUsers(){
        return userrepository.findAll();
    }

    public User findById(Long id){
        return userrepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userrepository.findByUsername(username);
    }

    public void deleteUser(Long id){
        userrepository.deleteById(id);
    }
}
