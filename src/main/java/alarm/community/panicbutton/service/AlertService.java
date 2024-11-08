package alarm.community.panicbutton.service;

import alarm.community.panicbutton.dto.AlertRequestDTO;
import alarm.community.panicbutton.model.Alert;
import alarm.community.panicbutton.model.User;
import alarm.community.panicbutton.repository.AlertRepository;
import alarm.community.panicbutton.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private UserRepository userRepository;

    public Alert registerAlert(AlertRequestDTO alertDTO) {
        Alert alert = new Alert();
        alert.setUsuario(userRepository.findById(alertDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        alert.setTipo_alert(alertDTO.getTipo_alert());
        alert.setMensaje(alertDTO.getMensaje());
        alert.setLatitud(alertDTO.getLatitud());
        alert.setLongitud(alertDTO.getLongitud());
        alert.setFecha_alerta(alertDTO.getFecha_alerta());

        return alertRepository.save(alert);
    }

    public Optional<Alert> updateAlert(Long id, AlertRequestDTO alertDTO) {
        Optional<Alert> alertOptional = alertRepository.findById(id);
        if (alertOptional.isPresent()) {
            Alert alert = alertOptional.get();

            // Actualizar los campos de la alerta
            alert.setTipo_alert(alertDTO.getTipo_alert());
            alert.setMensaje(alertDTO.getMensaje());
            alert.setLatitud(alertDTO.getLatitud());
            alert.setLongitud(alertDTO.getLongitud());

            // Si se proporciona un nuevo usuario, actualizarlo
            if (alertDTO.getUsuarioId() != null) {
                User user = userRepository.findById(alertDTO.getUsuarioId())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                alert.setUsuario(user);
            }

            // Si se proporciona una nueva fecha, actualizarla
            if (alertDTO.getFecha_alerta() != null) {
                alert.setFecha_alerta(alertDTO.getFecha_alerta());
            }

            alert = alertRepository.save(alert);
            return Optional.of(alert);
        }
        return Optional.empty();
    }

    public boolean deleteAlert(Long id) {
        if (alertRepository.existsById(id)) {
            alertRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
