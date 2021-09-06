package br.com.example.springshop.services;

import br.com.example.springshop.dto.AuthDTO;
import br.com.example.springshop.dto.LoginUserDTO;
import br.com.example.springshop.exceptions.UserNotFoundException;
import br.com.example.springshop.models.User;
import br.com.example.springshop.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;


    public Authentication authManagerAuthenticate(LoginUserDTO userDTO) throws UserNotFoundException {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if(Objects.isNull(user)) {
            log.error("User not found !", UserNotFoundException.class);
            throw new UserNotFoundException("Incorrect username or password !");
        }
        final Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getEmail(),
                        userDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
    }
}
