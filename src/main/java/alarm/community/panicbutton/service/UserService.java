package alarm.community.panicbutton.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alarm.community.panicbutton.dto.UserRequestDTO;
import alarm.community.panicbutton.model.User;
import alarm.community.panicbutton.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(UserRequestDTO userDTO) {
        User user = new User();
        user.setNombre(userDTO.getNombre());
        user.setApellido(userDTO.getApellido());
        user.setCedula(userDTO.getCedula());
        user.setEmail(userDTO.getEmail());
        user.setTelefono(userDTO.getTelefono());
        user.setContraseña(userDTO.getContraseña());
        user.setFecha_registro(userDTO.getFecha_registro());

        return userRepository.save(user);
    }

    public Optional<User> getUserByCedula(String cedula) {
        return userRepository.findByCedula(cedula);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean login(String cedula, String contraseña) {
        Optional<User> userOptional = userRepository.findByCedula(cedula);
        if (userOptional.isPresent()) {
            //User user = userOptional.get();
            return true;
        }
        return false;
    }

    public Optional<User> updateUser(String cedula, UserRequestDTO userDTO) {
        Optional<User> userOptional = userRepository.findByCedula(cedula);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNombre(userDTO.getNombre());
            user.setApellido(userDTO.getApellido());
            user.setEmail(userDTO.getEmail());
            user.setTelefono(userDTO.getTelefono());
            user.setContraseña(userDTO.getContraseña());
            
            user = userRepository.save(user);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
