package car_pooling.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import car_pooling.models.User;
import car_pooling.security.JwtUtil;
import car_pooling.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {

        User u = userService.loginAuth(user.getEmail(), user.getPassword());
       
        if (u == null) {
            throw new RuntimeException("Invalid credentials");
        }
         System.out.println(u.getId());

        String token = JwtUtil.generateToken(u.getEmail());

        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("userId", u.getId());
        res.put("name", u.getName());

        return res;
    }
}
