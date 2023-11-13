package co.edu.uniquindio.proyecto_viajes.client.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paquete implements Serializable {

    private String nombre;
    private int duracion;
    private String serviciosAdicionales;
    private double precio;
    private int cupoMax;
    private LocalDate fechaLimite;
    private ArrayList<Destino> destinos;



}
