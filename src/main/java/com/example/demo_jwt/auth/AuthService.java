package com.example.demo_jwt.auth;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo_jwt.Jwt.JwtService;
import com.example.demo_jwt.User.Role;
import com.example.demo_jwt.User.User;
import com.example.demo_jwt.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService JwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

   public AuthResponse login(LoginRequest request) {
        
        // 1. Usar el AuthenticationManager para validar las credenciales
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // 2. Si llega aquí, la autenticación fue exitosa. Cargar el usuario
        User user = userRepository.findByUsername(request.getUsername())
                                  .orElseThrow(); // O la excepción que prefieras

        // 3. Generar el token
        String token = JwtService.getToken(user); 

        // 4. Devolver la respuesta
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
         .username(request.getUsername())
         .password(passwordEncoder.encode(request.getPassword()))
         .role(Role.USER)
         .build();


        userRepository.save(user);

        return AuthResponse.builder()
        .token(JwtService.getToken(user))
        .build();
    }

}
