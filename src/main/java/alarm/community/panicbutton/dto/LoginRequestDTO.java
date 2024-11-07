package alarm.community.panicbutton.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDTO {
    
    @NotBlank(message = "La cedula no puede estar vacia")
    @Size(min=10, max= 10, message= "La cedula debe tener exactamente 10 caracteres")
    private String cedula;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String contraseña;
}
