package co.edu.uniquindio.proyecto_viajes.client.controller;

import co.edu.uniquindio.proyecto_viajes.DataPaquete;
import co.edu.uniquindio.proyecto_viajes.client.model.Paquete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReservaController implements Initializable {

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
    private TableColumn<?, ?> colNombrePaquete;

    @FXML
    private TableColumn<?, ?> colFechaLimite;

    @FXML
    private DatePicker dateFechaPlanificada;

    @FXML
    private TableView<?> tblReservas;

    @FXML
    private TextField txtCantidadPersonas;

    @FXML
    private TableView<Paquete> tblPaquetes;

    @FXML
    private TextField txtNumReserva;

    private ArrayList<Paquete> paquetesActualizados;

    private ObservableList<Paquete> paquetesObservables;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateListPaquetes();
    }

    public void updateListPaquetes(){
        try{

            Socket socket = new Socket("localhost",9595);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            DataPaquete dataPaquete = new DataPaquete("destino","listar",null);
            objectOutputStream.writeObject(dataPaquete);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            ArrayList<Paquete> paquetesRecibidos = (ArrayList<Paquete>) objectInputStream.readObject();

            objectOutputStream.close();
            objectInputStream.close();
            socket.close();

            this.paquetesActualizados = paquetesRecibidos;

            paquetesObservables = FXCollections.observableArrayList();
            this.colNombrePaquete.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            this.colFechaLimite.setCellValueFactory(new PropertyValueFactory<>("fechaLimite"));

            this.paquetesObservables.addAll(this.paquetesActualizados);
            this.tblPaquetes.setItems(paquetesObservables);





        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
