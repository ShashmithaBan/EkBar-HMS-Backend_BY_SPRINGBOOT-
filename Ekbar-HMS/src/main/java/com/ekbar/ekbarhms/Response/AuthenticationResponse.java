package com.ekbar.ekbarhms.Response;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String token;

    private String message;

}
