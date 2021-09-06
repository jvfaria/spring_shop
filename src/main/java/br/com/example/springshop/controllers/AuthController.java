package br.com.example.springshop.controllers;

import br.com.example.springshop.dto.AuthDTO;
import br.com.example.springshop.dto.AuthToken;
import br.com.example.springshop.dto.LoginUserDTO;
import br.com.example.springshop.exceptions.BadCredentialsException;
import br.com.example.springshop.exceptions.UserNotFoundException;
import br.com.example.springshop.security.TokenProvider;
import br.com.example.springshop.services.AuthService;
import br.com.example.springshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthService authService;

    @Autowired
    TokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;


    @PostMapping(value = "/auth")
    public ResponseEntity<?> generateToken(@RequestBody LoginUserDTO loginUser) throws UserNotFoundException {
        try {
            Authentication auth = authService.authManagerAuthenticate(loginUser);
            final String token = jwtTokenProvider.generateToken(auth);
            return new ResponseEntity<>(new AuthToken(token), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Incorrect username or password !");

        }
    }
}
