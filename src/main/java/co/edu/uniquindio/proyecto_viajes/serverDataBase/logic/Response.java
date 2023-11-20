package co.edu.uniquindio.proyecto_viajes.serverDataBase.logic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response implements Serializable {

    private String mensaje;
    private Object objetoRespuesta;
}
