package br.com.example.springshop.services;

import br.com.example.springshop.exceptions.EmailExistsException;
import br.com.example.springshop.exceptions.GlobalExceptionHandler;
import br.com.example.springshop.exceptions.ResourceNotFoundException;
import br.com.example.springshop.models.User;
import br.com.example.springshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public User create(User userDTO) throws EmailExistsException {
        if(this.emailExists(userDTO.getEmail())) {
            throw new EmailExistsException("Email já cadastrado ! Informar outro email.");
        }
        User user = new User();
        user.setName(userDTO.getName().toUpperCase(Locale.ROOT));
        user.setEmail(userDTO.getEmail());
        user.setPassword(encoder.encode(userDTO.getPassword()));

        return userRepository.save(user);
    }

    public void delete(Long id) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) {
            throw new ResourceNotFoundException("User ID not found: " + id);
        }
        userRepository.delete(user.get());
    }

    public boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        return Objects.nonNull(user) ? true : false;
    }
}
