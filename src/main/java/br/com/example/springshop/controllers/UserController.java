package br.com.example.springshop.controllers;

import br.com.example.springshop.exceptions.EmailExistsException;
import br.com.example.springshop.exceptions.GlobalExceptionHandler;
import br.com.example.springshop.exceptions.ResourceNotFoundException;
import br.com.example.springshop.models.User;
import br.com.example.springshop.repositories.UserRepository;
import br.com.example.springshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listAll(@RequestParam(value = "name", required = false) Optional<String> name) throws Exception {
        try {
            List<User> users;
            if(name.isPresent()) {
                users = userRepository.findByNameContains(name.get().toUpperCase(Locale.ROOT));
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
            users = userRepository.findAll();

            return !users.isEmpty() ? new ResponseEntity<>(users, HttpStatus.OK) : new ResponseEntity("No users founded",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<User> createUser(@RequestBody User user) throws EmailExistsException {
        User createdUser = userService.create(user);
        URI location = URI.create(String.format("users/%s", createdUser.getId()));
        return ResponseEntity.created(location).body(createdUser);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        userService.delete(userId);
        return new ResponseEntity(userId, HttpStatus.NO_CONTENT);
    }
}
