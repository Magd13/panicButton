package alarm.community.panicbutton.controller;

import alarm.community.panicbutton.dto.AlertRequestDTO;
import alarm.community.panicbutton.model.Alert;
import alarm.community.panicbutton.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {
    
    @Autowired
    private AlertService alertService;

    @PostMapping("/register")
    public ResponseEntity<Alert> registerAlert(@RequestBody AlertRequestDTO alertDTO) {
        if (alertDTO.getFecha_alerta() == null) {
            alertDTO.setFecha_alerta(LocalDateTime.now());
        }
        Alert alert = alertService.registerAlert(alertDTO);
        return ResponseEntity.status(201).body(alert);
    }
}