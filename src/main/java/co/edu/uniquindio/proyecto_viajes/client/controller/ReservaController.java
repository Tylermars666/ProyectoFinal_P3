package co.edu.uniquindio.proyecto_viajes.client.controller;

import co.edu.uniquindio.proyecto_viajes.DataPaquete;
import co.edu.uniquindio.proyecto_viajes.client.model.Destino;
import co.edu.uniquindio.proyecto_viajes.client.model.Guia;
import co.edu.uniquindio.proyecto_viajes.client.model.Paquete;
import co.edu.uniquindio.proyecto_viajes.client.model.Reserva;
import co.edu.uniquindio.proyecto_viajes.exception.RegistroExistenteException;
import co.edu.uniquindio.proyecto_viajes.serverDataBase.logic.Response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReservaController implements Initializable {

    @FXML
    private ComboBox<String> cmbListaGuias;

    @FXML
    private TableColumn<?, ?> colEstadoReserva;

    @FXML
    private TableColumn<?, ?> colFechaPlanificada;

    @FXML
    private TableColumn<?, ?> colFechaSolicitud;

    @FXML
    private TableColumn<?, ?> colNombrePaquete;

    @FXML
    private TableColumn<?, ?> colFechaLimite;

    @FXML
    private DatePicker dateFechaPlanificada;

    @FXML
    private TableView<Reserva> tblReservas;

    @FXML
    private TextField txtCantidadPersonas;

    @FXML
    private TableView<Paquete> tblPaquetes;

    private ArrayList<Paquete> paquetesActualizados;
    private ArrayList<Reserva> reservasActualizadas;

    private ObservableList<Paquete> paquetesObservables;
    private ObservableList<Reserva> reservasObservables;
    private ObservableList<String> guiasObservables;

    Reserva reservaSeleccionada;
    Paquete paqueteSeleccionado;
    LocalDate fechaPlanificada;
    String cantidadPersonas;
    String guiaSeleccionado;

    @FXML
    void agregarReserva(ActionEvent event) {

        try{           //SERVIDOR EN LOCALHOST EN ESTE CASO
            Socket socket = new Socket("localhost",9595);


            cantidadPersonas = this.txtCantidadPersonas.getText();

            if(cantidadPersonas.isBlank()||fechaPlanificada==null||guiaSeleccionado.isBlank()||paqueteSeleccionado==null){
                new Alert(Alert.AlertType.ERROR,"Asegurese de llenar todos los campos",ButtonType.OK).showAndWait();
            }else{


                Reserva reservaCreada = new Reserva(LocalDate.now(),fechaPlanificada,LoginController.clienteLogeado,Integer.parseInt(cantidadPersonas),paqueteSeleccionado,new Guia(guiaSeleccionado,"9988",null,7),"pendiente");
                DataPaquete paqueteDatos = new DataPaquete("reserva","crear",reservaCreada);


                ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                flujoSalida.writeObject(paqueteDatos);
                flujoSalida.flush();


                ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                Response response = (Response) flujoEntrada.readObject();
                ArrayList<Reserva> reservasRecibidas = (ArrayList<Reserva>) response.getObjetoRespuesta();

                flujoSalida.close();
                flujoEntrada.close();
                socket.close();

                if(response.getMensaje().equalsIgnoreCase("guardado")){
                    new Alert(Alert.AlertType.CONFIRMATION,"Reserva agregada con éxito").showAndWait();
                    updateListReservas();
                }else{
                    throw new RegistroExistenteException();
                }
            }



        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (RegistroExistenteException e){
            e.getAlert().showAndWait();
        }



    }

    @FXML
    void cancelarReserva(ActionEvent event) {

        Reserva reservaSeleccionada = this.tblReservas.getSelectionModel().getSelectedItem();

        if(reservaSeleccionada!=null){
            try{

                DataPaquete paquete = new DataPaquete("reserva","eliminar",reservaSeleccionada);
                Socket socket = new Socket("localhost",9595);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(paquete);

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Response response = (Response) objectInputStream.readObject();

                objectInputStream.close();
                objectOutputStream.close();
                socket.close();

                if(response.getMensaje().equalsIgnoreCase("eliminado")){

                    new Alert(Alert.AlertType.CONFIRMATION,"Reserva cancelada con exito",ButtonType.OK).showAndWait();
                    updateListReservas();
                }else{

                    new Alert(Alert.AlertType.ERROR,"La reserva que tratar de cancelar, no existe en la base de datos",ButtonType.OK).showAndWait();

                }



            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    @FXML
    void elegirFechaReserva(ActionEvent event) {

        fechaPlanificada = this.dateFechaPlanificada.getValue();

    }

    @FXML
    void elegirGuia(ActionEvent event) {

        guiaSeleccionado = this.cmbListaGuias.getSelectionModel().getSelectedItem();

    }

    @FXML
    void seleccionarPaquete(MouseEvent event) {

        paqueteSeleccionado = this.tblPaquetes.getSelectionModel().getSelectedItem();

    }

    @FXML
    void seleccionarReserva(MouseEvent event) {

        reservaSeleccionada = this.tblReservas.getSelectionModel().getSelectedItem();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateListPaquetes();
        updateListReservas();
        guiasObservables = FXCollections.observableArrayList("Carlos","Eliana","Omar");
        this.cmbListaGuias.setItems(guiasObservables);


    }

    public void updateListPaquetes(){
        try{

            Socket socket = new Socket("localhost",9595);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            DataPaquete dataPaquete = new DataPaquete("paquete","listar",null);
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

    public void updateListReservas(){
        try{

            Socket socket = new Socket("localhost",9595);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            DataPaquete dataPaquete = new DataPaquete("reserva","listar",null);
            objectOutputStream.writeObject(dataPaquete);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            ArrayList<Reserva> reservasRecibidas = (ArrayList<Reserva>) objectInputStream.readObject();

            objectOutputStream.close();
            objectInputStream.close();
            socket.close();

            this.reservasActualizadas = reservasRecibidas;

            reservasObservables = FXCollections.observableArrayList();
            this.colFechaSolicitud.setCellValueFactory(new PropertyValueFactory<>("fechaSolicitud"));
            this.colFechaPlanificada.setCellValueFactory(new PropertyValueFactory<>("fechaPlanificada"));
            this.colEstadoReserva.setCellValueFactory(new PropertyValueFactory<>("estadoReserva"));

            this.reservasObservables.addAll(this.reservasActualizadas);
            this.tblReservas.setItems(reservasObservables);





        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
