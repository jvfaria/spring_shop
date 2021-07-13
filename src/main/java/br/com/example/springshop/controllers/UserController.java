package br.com.example.springshop.controllers;

import br.com.example.springshop.models.User;
import br.com.example.springshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> listAllUsers(@RequestParam(value = "name", required = false) Optional<String> name) throws Exception {
        try {
            if(!name.isPresent()) {
                return userRepository.findByNameContains(name.get());
            }
            return userRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@RequestBody User user) throws Exception {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
