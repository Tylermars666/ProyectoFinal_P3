package co.edu.uniquindio.proyecto_viajes.client.model;

import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {


    private String nombreCompleto;
    private String identificacion;
    private String email;
    private String numTelefono;
    private String residencia;
}
