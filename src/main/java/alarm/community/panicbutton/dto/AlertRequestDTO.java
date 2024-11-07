package alarm.community.panicbutton.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AlertRequestDTO {
    
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @PastOrPresent(message = "La fecha de alerta debe ser actual o pasada")
    private LocalDateTime fecha_alerta;
}
