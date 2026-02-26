package com.treinamento.api.demo.controller;

import com.treinamento.api.demo.dto.LoginRequest;
import com.treinamento.api.demo.dto.LoginResponse;
import com.treinamento.api.demo.model.Usuario;
import com.treinamento.api.demo.repository.UsuarioRepository;
import com.treinamento.api.demo.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        log.info("Tentativa de login para email: {}", request.getEmail());
        
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        String token = jwtUtil.generateToken(usuario.getEmail());
        
        return ResponseEntity.ok(new LoginResponse(
            token,
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getRole()
        ));
    }
    
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@Valid @RequestBody Usuario usuario) {
        log.info("Registrando novo usuário: {}", usuario.getEmail());
        
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email já cadastrado");
        }
        
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setRole("USER");
        
        usuarioRepository.save(usuario);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuário cadastrado com sucesso!");
    }
}