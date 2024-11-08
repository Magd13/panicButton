package alarm.community.panicbutton.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alarm.community.panicbutton.dto.AlertRequestDTO;
import alarm.community.panicbutton.model.Alert;
import alarm.community.panicbutton.service.AlertService;

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

    @PutMapping("/{id}")
    public ResponseEntity<Alert> updateAlert(@PathVariable Long id, @RequestBody AlertRequestDTO alertDTO) {
        Optional<Alert> updatedAlert = alertService.updateAlert(id, alertDTO);
        return updatedAlert.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        boolean isDeleted = alertService.deleteAlert(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}