package alarm.community.panicbutton.service;

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
        user.setEmail(userDTO.getEmail());
        user.setTelefono(userDTO.getTelefono());
        user.setContraseña(userDTO.getContraseña());
        user.setFecha_registro(userDTO.getFecha_registro());

        return userRepository.save(user);
    }

}
