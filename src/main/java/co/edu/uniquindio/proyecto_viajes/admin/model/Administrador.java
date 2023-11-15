package co.edu.uniquindio.proyecto_viajes.admin.model;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrador implements Serializable {

    private String nombre;
    private String clave;


}
