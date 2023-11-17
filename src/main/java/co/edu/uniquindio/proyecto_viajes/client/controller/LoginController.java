package co.edu.uniquindio.proyecto_viajes.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private ComboBox<?> cmbRol;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtIdentificacion;

    @FXML
    void abrirVentanaRegistro(MouseEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/client/view/registro-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Registro");
        stage.setScene(scene);

        RegistroController controller = loader.getController();
        stage.show();

    }

    @FXML
    void elegirRol(ActionEvent event) {

    }

    @FXML
    void iniciarSesion(ActionEvent event) {

    }

}
