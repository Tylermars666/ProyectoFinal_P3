package co.edu.uniquindio.proyecto_viajes.admin.controller;

import co.edu.uniquindio.proyecto_viajes.client.model.Destino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DestinosController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCiudadDestino;

    @FXML
    private TableColumn<?, ?> colClimaDestino;

    @FXML
    private TableColumn<?, ?> colNombreDestino;

    @FXML
    private TableView<Destino> tblDestino;

    @FXML
    private TextField txtCiudadDestino;

    @FXML
    private TextField txtClimaDestino;

    @FXML
    private TextArea txtDescripcionDestino;

    @FXML
    private TextField txtNombreDestino;

    @FXML
    private TextField txtRutaImg1;

    @FXML
    private TextField txtRutaImg2;

    @FXML
    private TextField txtRutaImg3;

    @FXML
    void agregarDestino(ActionEvent event) {

    }

    @FXML
    void editarDestino(ActionEvent event) {

    }

    @FXML
    void eliminarDestino(ActionEvent event) {

    }

    @FXML
    void seleccionarDestino(MouseEvent event) {

    }

    @FXML
    void seleccionarRuta1(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar foto");

        FileChooser.ExtensionFilter imagen = new FileChooser.ExtensionFilter("Archivos de imagen", "*.jpg");
        fileChooser.getExtensionFilters().add(imagen);

        java.io.File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            this.txtRutaImg1.clear();
            String filePath = selectedFile.getAbsolutePath();
            this.txtRutaImg1.setText(filePath);

        }
    }

    @FXML
    void seleccionarRuta2(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar foto");

        FileChooser.ExtensionFilter imagen = new FileChooser.ExtensionFilter("Archivos de imagen", "*.jpg");
        fileChooser.getExtensionFilters().add(imagen);

        java.io.File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            this.txtRutaImg2.clear();
            String filePath = selectedFile.getAbsolutePath();
            this.txtRutaImg2.setText(filePath);

        }

    }

    @FXML
    void seleccionarRuta3(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar foto");

        FileChooser.ExtensionFilter imagen = new FileChooser.ExtensionFilter("Archivos de imagen", "*.jpg");
        fileChooser.getExtensionFilters().add(imagen);

        java.io.File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            this.txtRutaImg3.clear();
            String filePath = selectedFile.getAbsolutePath();
            this.txtRutaImg3.setText(filePath);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
