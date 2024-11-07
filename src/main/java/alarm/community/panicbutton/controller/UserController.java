package alarm.community.panicbutton.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alarm.community.panicbutton.dto.LoginRequestDTO;
import alarm.community.panicbutton.dto.UserRequestDTO;
import alarm.community.panicbutton.model.User;
import alarm.community.panicbutton.service.UserService;
import jakarta.validation.Valid;

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

    @GetMapping("/{cedula}")
    public ResponseEntity<User> getUserByCedula(@PathVariable String cedula ) {
        Optional<User> user = userService.getUserByCedula(cedula);
        return user.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO loginDTO) {
        System.out.println("Login endpoint fue alcanzado");
        boolean loginSuccess = userService.login(loginDTO.getCedula(), loginDTO.getContraseña());
        if (loginSuccess) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @PutMapping("/{cedula}")
    public ResponseEntity<User> updateUser(@PathVariable String cedula, @RequestBody UserRequestDTO userDTO) {
        Optional<User> updateUser = userService.updateUser(cedula, userDTO);
        return updateUser.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean idDelete = userService.deleteUser(id);
        if (idDelete) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
