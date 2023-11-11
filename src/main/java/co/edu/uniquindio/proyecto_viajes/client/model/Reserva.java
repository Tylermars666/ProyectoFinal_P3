package co.edu.uniquindio.proyecto_viajes.client.model;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

    private LocalDate fechaSolicitud;
    private LocalDate fechaPlanificada;
    private Cliente cliente;
    private int cantidadPersonas;
    private Paquete paquete;
    private Guia guia;
    private String estadoReserva;

}
