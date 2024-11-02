package alarm.community.panicbutton.model;
import lombok.Data;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message="El apellido no puede estar vacio")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacio")
    @Email(message = "El amail debe estar en un formato valido")
    @Column(unique= true)
    private String email;

    @Pattern(regexp = "\\d{10}$", message = "El número de télefono debe contener 10 dígitos")
    private String telefono;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 8, max = 20, message = "La contraseña debe tener al menos 8 y máximo 20 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
             message = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula y un número")
    private String contraseña;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @FutureOrPresent
    private LocalDateTime fecha_registro;
}
