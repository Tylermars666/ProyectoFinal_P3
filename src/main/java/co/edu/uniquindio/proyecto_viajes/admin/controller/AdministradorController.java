package co.edu.uniquindio.proyecto_viajes.admin.controller;

import co.edu.uniquindio.proyecto_viajes.exception.ComboBoxVacioException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdministradorController implements Initializable {

    @FXML
    private ComboBox<String> cmbOpcionesVentanas;

    ObservableList<String> opcionesVentanaAdmin;

    String ventanaSeleccionada;

    @FXML
    void abrirVentana(ActionEvent event) {

        try{

            if(ventanaSeleccionada==null){
                throw new ComboBoxVacioException();
            }

            switch (ventanaSeleccionada){

                case "Destinos":
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/admin/view/destinos-view.fxml"));

                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Destinos");
                    stage.setScene(scene);

                    DestinosController controller = loader.getController();
                    stage.show();

                    break;
                case "Guias":

                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/admin/view/guias-view.fxml"));

                    Scene scene1 = new Scene(loader1.load());
                    Stage stage1 = new Stage();
                    stage1.setTitle("Guias");
                    stage1.setScene(scene1);

                    GuiasController controller1 = loader1.getController();
                    stage1.show();
                    break;

                case "Paquetes":

                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/admin/view/paquetes-view.fxml"));

                    Scene scene2 = new Scene(loader2.load());
                    Stage stage2 = new Stage();
                    stage2.setTitle("Paquetes");
                    stage2.setScene(scene2);

                    PaquetesController controller2 = loader2.getController();
                    stage2.show();
                    break;

                default:

                    FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/admin/view/estadisticas-view.fxml"));

                    Scene scene3 = new Scene(loader3.load());
                    Stage stage3 = new Stage();
                    stage3.setTitle("Estadisticas");
                    stage3.setScene(scene3);

                    EstadisticasController controller3 = loader3.getController();
                    stage3.show();
                    break;

            }


        }catch (ComboBoxVacioException e){

            e.getAlert().showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void elegirVentanaAdministrador(ActionEvent event) {

        ventanaSeleccionada = cmbOpcionesVentanas.getSelectionModel().getSelectedItem();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        opcionesVentanaAdmin = FXCollections.observableArrayList("Destinos","Guias","Paquetes","Estadisticas");
        cmbOpcionesVentanas.setItems(opcionesVentanaAdmin);

    }
}
