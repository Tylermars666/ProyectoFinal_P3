package co.edu.uniquindio.proyecto_viajes.client.model;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva implements Serializable {

    private LocalDate fechaSolicitud;
    private LocalDate fechaPlanificada;
    private Cliente cliente;
    private int cantidadPersonas;
    private Paquete paquete;
    private Guia guia;
    private String estadoReserva;

}
