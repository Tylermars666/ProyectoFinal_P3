package co.edu.uniquindio.proyecto_viajes.client.controller;

import co.edu.uniquindio.proyecto_viajes.admin.controller.AdministradorController;
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
    void iniciarSesion(ActionEvent event) throws Exception {

        //DEPENDIENDO DEL ROL SE ABRE VENTANA PRINCIPAL CLIENTES O VENTANA PRINCIPAL ADMINISTRADOR

        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/admin/view/admin-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Administrador");
        stage.setScene(scene);

        AdministradorController controller = loader.getController();
        stage.show();*/

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/client/view/principal-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Principal");
        stage.setScene(scene);

        PrincipalController controller = loader.getController();
        stage.show();

    }

}
