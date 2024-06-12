package com.ekbar.ekbarhms.Response;

import com.ekbar.ekbarhms.Model.User;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private String role;
}
