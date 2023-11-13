package co.edu.uniquindio.proyecto_viajes.client.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {


    private String nombreCompleto;
    private String identificacion;
    private String email;
    private String numTelefono;
    private String residencia;
}
