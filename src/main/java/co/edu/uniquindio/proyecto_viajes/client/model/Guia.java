package co.edu.uniquindio.proyecto_viajes.client.model;

import java.util.ArrayList;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Guia {

    private String nombre;
    private String identificacion;
    private ArrayList<String> idiomas;
    private int Experiencia;

}
