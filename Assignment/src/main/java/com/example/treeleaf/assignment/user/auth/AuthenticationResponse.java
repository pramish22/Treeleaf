package com.example.treeleaf.assignment.user.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {

    private String username;

    private String authenticationToken;
}
