package alarm.community.panicbutton.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alarm.community.panicbutton.dto.UserRequestDTO;
import alarm.community.panicbutton.model.User;
import alarm.community.panicbutton.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRequestDTO userDTO) {
        if (userDTO.getFecha_registro() == null) {
        userDTO.setFecha_registro(LocalDate.now()); 
        }
        User user = userService.registerUser(userDTO);
        return ResponseEntity.status(201).body(user);

    }
}
