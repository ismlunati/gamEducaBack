package com.gameduca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    // standard constructors
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<Usero> getUsers() {
        return (List<Usero>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody Usero user) {
        userRepository.save(user);
    }
}

