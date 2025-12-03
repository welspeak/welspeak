package com.assessment.sasing.controller;

import com.assessment.sasing.model.User;
import com.assessment.sasing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        User user = userRepository.findByUsername(username)
                .orElse(null);

        Map<String, Object> resp = new HashMap<>();
        if (user == null || !user.getPassword().equals(password)) {
            resp.put("error", "Invalid username or password");
            return resp;
        }

        resp.put("id", user.getId());
        resp.put("username", user.getUsername());
        resp.put("name", user.getName());
        resp.put("role", user.getRole());
        resp.put("token", "dummy-token"); // For now, we just send a dummy token
        return resp;
    }
}
