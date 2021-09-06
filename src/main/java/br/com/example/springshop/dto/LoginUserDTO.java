package br.com.example.springshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginUserDTO {
    private String email;
    private String password;
}
