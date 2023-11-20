package co.edu.uniquindio.proyecto_viajes.exception;

import javafx.scene.control.Alert;

public class RegistroExistenteException extends Exception{

    public Alert getAlert(){

        return new Alert(Alert.AlertType.ERROR,"Ya existe un registro identico en la base de datos");

    }

}
