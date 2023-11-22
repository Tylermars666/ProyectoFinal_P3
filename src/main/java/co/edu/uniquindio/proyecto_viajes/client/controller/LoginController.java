package co.edu.uniquindio.proyecto_viajes.client.controller;

import co.edu.uniquindio.proyecto_viajes.admin.controller.AdministradorController;
import co.edu.uniquindio.proyecto_viajes.admin.model.AdminLogeado;
import co.edu.uniquindio.proyecto_viajes.client.logic.ClienteLogeado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private ComboBox<String> cmbRol;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtIdentificacion;

    private ObservableList<String> opcionesCombo;

    private String rolSeleccionado;

    public static ClienteLogeado clienteLogeado;
    public static AdminLogeado adminLogeado;

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

        rolSeleccionado = cmbRol.getSelectionModel().getSelectedItem();

    }

    @FXML
    void iniciarSesion(ActionEvent event) throws Exception {


        if(!rolSeleccionado.isBlank()){

            if(rolSeleccionado.equalsIgnoreCase("cliente")){



                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/client/view/principal-view.fxml"));

                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("Principal");
                stage.setScene(scene);

                PrincipalController controller = loader.getController();
                stage.show();
            }else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/admin/view/admin-view.fxml"));

                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("Administrador");
                stage.setScene(scene);

                AdministradorController controller = loader.getController();
                stage.show();
            }

        }else{

            new Alert(Alert.AlertType.ERROR,"Valide los datos ingresados", ButtonType.OK).showAndWait();
        }





    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        opcionesCombo = FXCollections.observableArrayList("Cliente","Administrador");
        this.cmbRol.setItems(opcionesCombo);


    }
}
