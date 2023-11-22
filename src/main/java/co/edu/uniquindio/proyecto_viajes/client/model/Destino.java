package co.edu.uniquindio.proyecto_viajes.client.model;


import java.io.Serializable;
import java.util.ArrayList;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Destino implements Serializable {

    private String nombre;
    private String ciudad;
    private ArrayList<byte[]> imagenes;
    private String clima;
    private String descripcion;

}
