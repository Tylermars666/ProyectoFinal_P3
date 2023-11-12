package co.edu.uniquindio.proyecto_viajes.client.model;


import java.util.ArrayList;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Destino {

    private String nombre;
    private String ciudad;
    private ArrayList<String> imagenes;
    private String clima;

}
