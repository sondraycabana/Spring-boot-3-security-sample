package com.example.Geeksample.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    private String email;

    private String password;

    private String fullName;

    // getters and setters here...
}