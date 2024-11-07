package alarm.community.panicbutton.service;

import alarm.community.panicbutton.dto.AlertRequestDTO;
import alarm.community.panicbutton.model.Alert;
import alarm.community.panicbutton.model.User;
import alarm.community.panicbutton.repository.AlertRepository;
import alarm.community.panicbutton.repository.UserRepository;
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
}
