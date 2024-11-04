package alarm.community.panicbutton.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name="alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull(message = "El campo usuario no debe ser nulo")
    private User usuario;

    @NotEmpty(message = "El campo tipo de alerta no debe estar vacío o nulo")
    @Pattern(regexp = "Emergencia|Precaución", message = "El tipo de alerta debe ser 'Emergencia' o 'Precaución'")
    private String tipo_alert;

    private String mensaje; 
    @NotNull(message = "La latitud no debe ser nula")
    @Min(value = -90, message = "La latitud mínima es -90")
    @Max(value = 90, message = "La latitud máxima es 90")
    private Double latitud;

    @NotNull(message = "La longitud no debe ser nula")
    @Min(value = -180, message = "La longitud mínima es -180")
    @Max(value = 180, message = "La longitud máxima es 180")
    private Double longitud;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @PastOrPresent(message = "La fecha de alerta debe ser actual o pasada")
    private LocalDateTime fecha_alerta;
}
