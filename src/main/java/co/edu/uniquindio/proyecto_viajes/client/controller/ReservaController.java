package co.edu.uniquindio.proyecto_viajes.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ReservaController {

    @FXML
    private ComboBox<?> cmbListaGuias;

    @FXML
    private TableColumn<?, ?> colEstadoReserva;

    @FXML
    private TableColumn<?, ?> colFechaPlanificada;

    @FXML
    private TableColumn<?, ?> colFechaSolicitud;

    @FXML
    private TableColumn<?, ?> colNombreGuia;

    @FXML
    private DatePicker dateFechaPlanificada;

    @FXML
    private TableView<?> tblReservas;

    @FXML
    private TextField txtCantidadPersonas;

    @FXML
    private TextField txtNumReserva;

    @FXML
    void agregarReserva(ActionEvent event) {

    }

    @FXML
    void cancelarReserva(ActionEvent event) {

    }

    @FXML
    void elegirFechaReserva(ActionEvent event) {

    }

    @FXML
    void elegirGuia(ActionEvent event) {

    }

}
