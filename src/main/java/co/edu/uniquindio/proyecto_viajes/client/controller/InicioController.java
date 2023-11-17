package co.edu.uniquindio.proyecto_viajes.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class InicioController implements Initializable {

    @FXML
    private ComboBox<?> cmbFiltroBusqueda;

    @FXML
    private TableColumn<?, ?> colCiudad;

    @FXML
    private TableColumn<?, ?> colClima;

    @FXML
    private TableColumn<?, ?> colCupoPaquete;

    @FXML
    private TableColumn<?, ?> colDuracionPaquete;

    @FXML
    private TableColumn<?, ?> colFechaLimitePaquete;

    @FXML
    private TableColumn<?, ?> colNombrePaquete;

    @FXML
    private TableColumn<?, ?> colPrecioPaquete;

    @FXML
    private ImageView imgImagenCiudad;

    @FXML
    private ImageView imgLogoPrincipal;

    @FXML
    private TableView<?> tblCiudad;

    @FXML
    private TableView<?> tblPaquetes;

    @FXML
    private TextArea txtADescripcionDestino;

    @FXML
    private TextField txtBusqueda;

    @FXML
    private Button btnAnterior;

    @FXML
    private Button btnSiguiente;

    @FXML
    private Label lblDescripcion;

    @FXML
    void abrirLogin(MouseEvent event) {

        //Abre Ventana Login

    }

    @FXML
    void buscar(ActionEvent event) {

    }

    @FXML
    void seleccionarCiudad(MouseEvent event) {

    }

    @FXML
    void seleccionarDestino(MouseEvent event) {

    }

    @FXML
    void seleccionarFiltro(ActionEvent event) {

    }

    @FXML
    void verAnteriorImagen(ActionEvent event) {

    }

    @FXML
    void verSiguienteImagen(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.txtADescripcionDestino.setVisible(false);
        this.btnAnterior.setVisible(false);
        this.btnSiguiente.setVisible(false);
        this.lblDescripcion.setVisible(false);



    }
}
