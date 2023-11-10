package co.edu.uniquindio.proyecto_viajes.client.model;

import java.time.LocalDate;

public class Reserva {

    public LocalDate fechaSolicitud;
    public LocalDate fechaPlanificada;
    public Cliente cliente;
    public int cantidadPersonas;
    public Paquete paquete;
    public Guia guia;
    public String estadoReserva;

}
