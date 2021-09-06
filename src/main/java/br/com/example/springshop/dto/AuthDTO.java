package br.com.example.springshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AuthDTO {
    private String email;
    private List<String> roles;
}
