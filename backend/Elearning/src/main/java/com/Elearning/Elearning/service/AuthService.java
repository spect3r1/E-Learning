package com.Elearning.Elearning.service;

import com.Elearning.Elearning.DAO.UserDAO;
import com.Elearning.Elearning.DTO.AuthResponse;
import com.Elearning.Elearning.DTO.LoginRequest;
import com.Elearning.Elearning.DTO.RegisterRequest;
import com.Elearning.Elearning.DTO.UserResponse;
import com.Elearning.Elearning.Models.User;
import com.Elearning.Elearning.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public AuthService(UserDAO userDAO,JwtUtil jwtUtil) {
        this.userDAO = userDAO;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(RegisterRequest request) {
        User existing = userDAO.findByEmail(request.getEmail());
        if (existing != null) {
            throw new RuntimeException("Email already exists");
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        userDAO.createUser(
                request.getName(),
                request.getEmail(),
                hashedPassword,
                "STUDENT"
        );
        User createdUser = userDAO.findByEmail(request.getEmail());
        if (createdUser == null) {
            throw new RuntimeException("Failed to register user");

        }
        String token = jwtUtil.generateToken(
                createdUser.getId(),
                createdUser.getEmail(),
                createdUser.getRole()
        );
        UserResponse userResponse = new UserResponse(
                createdUser.getId(),
                createdUser.getName(),
                createdUser.getEmail(),
                createdUser.getRole()
        );
        return new AuthResponse(token, userResponse);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userDAO.findByEmail(request.getEmail());
        if(user == null){
            throw new RuntimeException("Invalid email or password");
        }
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }
        String token = jwtUtil.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
        return new AuthResponse(token, userResponse);
    }
}
