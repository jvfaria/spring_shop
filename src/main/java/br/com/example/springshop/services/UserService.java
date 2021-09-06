package br.com.example.springshop.services;

import br.com.example.springshop.config.CustomPasswordEncoder;
import br.com.example.springshop.exceptions.EmailExistsException;
import br.com.example.springshop.exceptions.ResourceNotFoundException;
import br.com.example.springshop.models.User;
import br.com.example.springshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    CustomPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    public User create(User userDTO) throws EmailExistsException {
        if(this.emailExists(userDTO.getEmail())) {
            throw new EmailExistsException("Email já cadastrado ! Informar outro email.");
        }
        User user = new User();
        user.setUsername(userDTO.getUsername().toUpperCase(Locale.ROOT));
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.getEncoder().encode(userDTO.getPassword()));

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

    private Set<? extends SimpleGrantedAuthority> getAuthorities(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not found !");
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
        return userDetails;
    }
}
