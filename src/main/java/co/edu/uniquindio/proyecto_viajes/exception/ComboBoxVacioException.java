package co.edu.uniquindio.proyecto_viajes.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ComboBoxVacioException extends Exception{

    public Alert getAlert(){

        return new Alert(Alert.AlertType.ERROR,"No se eligió ninguna opción", ButtonType.OK);

    }
}
