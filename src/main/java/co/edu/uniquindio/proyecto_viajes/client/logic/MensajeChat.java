package co.edu.uniquindio.proyecto_viajes.client.logic;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeChat implements Serializable {

    private String nickRemitente, ipReceptor, cuerpoMensaje;


}
