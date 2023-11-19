package co.edu.uniquindio.proyecto_viajes.admin.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class GuiasController {

    @FXML
    private TableColumn<?, ?> colExperienciaGuia;

    @FXML
    private TableColumn<?, ?> colIdentificacionGuia;

    @FXML
    private TableColumn<?, ?> colNombreGuia;

    @FXML
    private TableView<?> tblGuias;

    @FXML
    private TextField txtExperienciaGuia;

    @FXML
    private TextField txtIdentificacionGuia;

    @FXML
    private TextField txtIdioma1Guia;

    @FXML
    private TextField txtIdioma2Guia;

    @FXML
    private TextField txtIdioma3Guia;

    @FXML
    private TextField txtNombreGuia;

    @FXML
    void agregarGuia(ActionEvent event) {

    }

    @FXML
    void editarGuia(ActionEvent event) {

    }

    @FXML
    void eliminarGuia(ActionEvent event) {

    }

    @FXML
    void seleccionarGuia(MouseEvent event) {

    }

}
