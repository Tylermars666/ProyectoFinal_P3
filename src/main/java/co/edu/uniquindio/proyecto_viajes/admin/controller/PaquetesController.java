package co.edu.uniquindio.proyecto_viajes.admin.controller;

import co.edu.uniquindio.proyecto_viajes.DataPaquete;
import co.edu.uniquindio.proyecto_viajes.client.model.Destino;
import co.edu.uniquindio.proyecto_viajes.client.model.Paquete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaquetesController implements Initializable {

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
    private TableColumn<?, ?> colCiudadDisponible;

    @FXML
    private TableColumn<?, ?> colCiudadSeleccionada;

    @FXML
    private TableColumn<?, ?> colClimaDisponible;

    @FXML
    private TableColumn<?, ?> colClimaSeleccionado;

    @FXML
    private DatePicker dateFechaPaquete;

    @FXML
    private ListView<String> listDestinosDisponibles;

    @FXML
    private ListView<String> listDestinosSeleccionados;

    @FXML
    private TableView<Paquete> tblPaquetes;

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

    private ArrayList<String> elementosElegidos;

    @FXML
    private TableView<Destino> tblDestinosDisponible;

    @FXML
    private TableView<?> tblDestinosSeleccionados;

    ArrayList<Paquete> paquetesActualizados;

    ArrayList<Destino> destinosActualizados;

    ObservableList<Paquete> paquetesObservables;

    ObservableList<Destino> destinosObservables;



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

    @FXML
    void seleccionarDestino(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateList();
        updateListDestinos();

    }

    public void updateList(){
        try{

            Socket socket = new Socket("localhost",9595);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            DataPaquete dataPaquete = new DataPaquete("paquete","listar",null);
            objectOutputStream.writeObject(dataPaquete);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            ArrayList<Paquete> paqueteRecibido = (ArrayList<Paquete>) objectInputStream.readObject();

            objectOutputStream.close();
            objectInputStream.close();
            socket.close();



            this.paquetesActualizados = paqueteRecibido;

            paquetesObservables = FXCollections.observableArrayList();
            this.colNombrePaquete.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            this.colDuracionPaquete.setCellValueFactory(new PropertyValueFactory<>("duracion"));
            this.colPrecioPaquete.setCellValueFactory(new PropertyValueFactory<>("precio"));
            this.colCupoPaquete.setCellValueFactory(new PropertyValueFactory<>("cupo"));
            this.colFechaPaquete.setCellValueFactory(new PropertyValueFactory<>("fecha"));


            this.paquetesObservables.addAll(this.paquetesActualizados);
            this.tblPaquetes.setItems(paquetesObservables);




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateListDestinos(){

        try{

            Socket socket = new Socket("localhost",9595);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            DataPaquete dataPaquete = new DataPaquete("destino","listar",null);
            objectOutputStream.writeObject(dataPaquete);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            ArrayList<Destino> destinoRecibido = (ArrayList<Destino>) objectInputStream.readObject();

            objectOutputStream.close();
            objectInputStream.close();
            socket.close();

            this.destinosActualizados = destinoRecibido;

            destinosObservables = FXCollections.observableArrayList();
            this.colCiudadDisponible.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
            this.colClimaDisponible.setCellValueFactory(new PropertyValueFactory<>("clima"));

            this.destinosObservables.addAll(this.destinosActualizados);
            this.tblDestinosDisponible.setItems(destinosObservables);




        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
