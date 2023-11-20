package co.edu.uniquindio.proyecto_viajes.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CamposVaciosException extends Exception {

    public Alert getAlert(){

        return new Alert(Alert.AlertType.ERROR,"Faltan campos por llenar", ButtonType.OK);

    }
}
