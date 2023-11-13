package co.edu.uniquindio.proyecto_viajes.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guia implements Serializable {

    private String nombreCompleto;
    private String identificacion;
    private ArrayList<String> idiomas;
    private int Experiencia;

}
