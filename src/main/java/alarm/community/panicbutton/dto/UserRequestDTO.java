package alarm.community.panicbutton.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser valido")
    private String email;

    @NotBlank(message = "El telefono es obligario")
    @Pattern(regexp = "\\d{10}$", message = "El número de télefono debe contener 10 dígitos")
    private String telefono;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 8, max = 20, message = "La contraseña debe tener al menos 8 y máximo 20 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
             message = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula y un número")
    private String contraseña;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate fecha_registro;
}

