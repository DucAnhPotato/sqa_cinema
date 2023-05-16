package com.spring.sqa_fullstack;

import com.spring.sqa_fullstack.model.Role;
import com.spring.sqa_fullstack.model.Seat;
import com.spring.sqa_fullstack.model.User;
import com.spring.sqa_fullstack.repository.SeatRepository;
import com.spring.sqa_fullstack.repository.UserRepository;
import com.spring.sqa_fullstack.service.SeatService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SqaFullstackApplication {

    @Autowired
    private UserRepository userRepository;



//    @PostConstruct
    public void init() {
        User admin = new User(),
                user = new User();
        createUser(admin, "admin", "admin", "admin", Role.ADMIN);
        createUser(user, "user", "user", "user", Role.USER);
    }

    public void createUser(User user, String name, String username, String password, Role role) {
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
    }

    public static void main(String[] args) {
        SpringApplication.run(SqaFullstackApplication.class, args);
    }

}
