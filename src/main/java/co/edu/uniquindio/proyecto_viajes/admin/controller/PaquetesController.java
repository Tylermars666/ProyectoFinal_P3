package co.edu.uniquindio.proyecto_viajes.admin.controller;

import co.edu.uniquindio.proyecto_viajes.DataPaquete;
import co.edu.uniquindio.proyecto_viajes.client.model.Destino;
import co.edu.uniquindio.proyecto_viajes.client.model.Paquete;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
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
    private TableView<Destino> tblDestinosSeleccionados;

    ArrayList<Paquete> paquetesActualizados;

    ArrayList<Destino> destinosActualizados;

    ArrayList<Destino> destinosSeleccionadosArray;

    ObservableList<Paquete> paquetesObservables;

    ObservableList<Destino> destinosObservables;

    ObservableList<Destino> destinosObservablesSeleccionados;

    LocalDate fechaSeleccionada;

    String nombre, duracion, precio, cupo, servicios;





    @FXML
    void agregarPaquete(ActionEvent event) {

        try{           //SERVIDOR EN LOCALHOST EN ESTE CASO
            Socket socket = new Socket("localhost",9595);


            nombre = this.txtNombrePaquete.getText();
            duracion = this.txtDuracionPaquete.getText();
            precio = this.txtPrecioPaquete.getText();
            cupo = this.txtCupoPaquete.getText();
            servicios = this.txtServiciosAdicionales.getText();

            if(servicios.isBlank()||nombre.isBlank()||duracion.isBlank()||precio.isBlank()||cupo.isBlank()||fechaSeleccionada==null||destinosSeleccionadosArray==null){
                new Alert(Alert.AlertType.ERROR,"Asegurese de llenar todos los campos",ButtonType.OK).showAndWait();
            }else{


                Paquete paqueteCreado = new Paquete(nombre,Integer.parseInt(duracion),servicios,Double.parseDouble(precio),Integer.parseInt(cupo),fechaSeleccionada,destinosSeleccionadosArray);
                DataPaquete paqueteDatos = new DataPaquete("paquete","crear",paqueteCreado);


                ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                flujoSalida.writeObject(paqueteDatos);
                flujoSalida.flush();


                ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                Response response = (Response) flujoEntrada.readObject();
                ArrayList<Paquete> paquetesRecibidos = (ArrayList<Paquete>) response.getObjetoRespuesta();

                flujoSalida.close();
                flujoEntrada.close();
                socket.close();

                if(response.getMensaje().equalsIgnoreCase("guardado")){
                    new Alert(Alert.AlertType.CONFIRMATION,"Paquete agregado con éxito").showAndWait();
                    this.txtNombrePaquete.clear();
                    this.txtCupoPaquete.clear();
                    this.txtDuracionPaquete.clear();
                    this.txtPrecioPaquete.clear();
                    this.txtServiciosAdicionales.clear();
                    this.destinosSeleccionadosArray.clear();
                    this.destinosObservablesSeleccionados.clear();
                    this.tblDestinosSeleccionados.refresh();

                    updateList();
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
    void editarPaquete(ActionEvent event) {

    }

    @FXML
    void eliminarPaquete(ActionEvent event) {

    }

    @FXML
    void seleccionarFechaPaquete(ActionEvent event) {

        fechaSeleccionada = this.dateFechaPaquete.getValue();

    }

    @FXML
    void seleccionarPaquete(MouseEvent event) {

        Paquete paqueteSeleccionado = this.tblPaquetes.getSelectionModel().getSelectedItem();

        if(paqueteSeleccionado!=null){

            this.txtNombrePaquete.setText(paqueteSeleccionado.getNombre());
            this.txtDuracionPaquete.setText(String.valueOf(paqueteSeleccionado.getDuracion()));
            this.txtPrecioPaquete.setText(String.valueOf(paqueteSeleccionado.getPrecio()));
            this.txtCupoPaquete.setText(String.valueOf(paqueteSeleccionado.getCupoMax()));
            this.txtServiciosAdicionales.setText(paqueteSeleccionado.getServiciosAdicionales());

        }
    }

    @FXML
    void seleccionarDestino(MouseEvent event) {

        Destino destinoSeleccionado = this.tblDestinosDisponible.getSelectionModel().getSelectedItem();

        if(destinoSeleccionado!=null){

            if(!existeDestinoEnArray(destinoSeleccionado)){


                this.colCiudadSeleccionada.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
                this.colClimaSeleccionado.setCellValueFactory(new PropertyValueFactory<>("clima"));
                destinosObservablesSeleccionados.add(destinoSeleccionado);
                this.tblDestinosSeleccionados.setItems(destinosObservablesSeleccionados);
                destinosSeleccionadosArray.add(destinoSeleccionado);
            }else{
                new Alert(Alert.AlertType.ERROR,"Ya agregaste este destino al paquete",ButtonType.OK).showAndWait();
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateList();
        updateListDestinos();
        destinosObservablesSeleccionados = FXCollections.observableArrayList();
        destinosSeleccionadosArray = new ArrayList<>();

    }

    public boolean existeDestinoEnArray(Destino destino){

        if(destinosSeleccionadosArray!=null){

            for(Destino destinoArray: destinosSeleccionadosArray){

                if(destinoArray.equals(destino)){
                    return true;
                }

            }
        }

        return false;

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
            this.colCupoPaquete.setCellValueFactory(new PropertyValueFactory<>("cupoMax"));
            this.colFechaPaquete.setCellValueFactory(new PropertyValueFactory<>("fechaLimite"));


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
