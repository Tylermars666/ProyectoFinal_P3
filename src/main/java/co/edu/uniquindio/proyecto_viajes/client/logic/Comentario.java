package co.edu.uniquindio.proyecto_viajes.client.logic;

import co.edu.uniquindio.proyecto_viajes.client.model.Cliente;
import co.edu.uniquindio.proyecto_viajes.client.model.Paquete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario implements Serializable {


    private String comentario;
    private Cliente clienteComenton;
    private Paquete paqueteComentado;
}
