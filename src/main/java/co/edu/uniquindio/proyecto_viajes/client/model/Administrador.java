package co.edu.uniquindio.proyecto_viajes.client.model;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Administrador implements Serializable {

    private String nombre;
    private String clave;


}
