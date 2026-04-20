package com.workshop.zarif.service;

import com.workshop.zarif.dto.AuthResponse;
import com.workshop.zarif.dto.LoginRequest;
import com.workshop.zarif.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}

