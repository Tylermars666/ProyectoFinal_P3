package co.edu.uniquindio.proyecto_viajes.admin.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PaquetesController {

    @FXML
    private TableColumn<?, ?> colCupoPaquete;

    @FXML
    private TableColumn<?, ?> colDuracionPaquete;

    @FXML
    private TableColumn<?, ?> colFechaPaquete;

    @FXML
    private TableColumn<?, ?> colNombrePaquete;

    @FXML
    private TableColumn<?, ?> colPrecioPaquete;

    @FXML
    private DatePicker dateFechaPaquete;

    @FXML
    private TableView<?> tblPaquetes;

    @FXML
    private TextField txtCupoPaquete;

    @FXML
    private TextField txtDuracionPaquete;

    @FXML
    private TextField txtNombrePaquete;

    @FXML
    private TextField txtPrecioPaquete;

    @FXML
    private TextArea txtServiciosAdicionales;

    @FXML
    void agregarPaquete(ActionEvent event) {

    }

    @FXML
    void editarPaquete(ActionEvent event) {

    }

    @FXML
    void eliminarPaquete(ActionEvent event) {

    }

    @FXML
    void seleccionarFechaPaquete(ActionEvent event) {

    }

    @FXML
    void seleccionarPaquete(MouseEvent event) {

    }

}
