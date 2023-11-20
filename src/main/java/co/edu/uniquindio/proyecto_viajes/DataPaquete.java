package co.edu.uniquindio.proyecto_viajes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataPaquete implements Serializable {

    private String nombreEntidad;
    private String request;
    private Object objeto;



}
