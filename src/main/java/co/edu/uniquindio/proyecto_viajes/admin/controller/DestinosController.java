package co.edu.uniquindio.proyecto_viajes.admin.controller;

import co.edu.uniquindio.proyecto_viajes.DataPaquete;
import co.edu.uniquindio.proyecto_viajes.client.model.Destino;
import co.edu.uniquindio.proyecto_viajes.exception.CamposVaciosException;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    private String nombre,clima,ciudad,descripcion,ruta1,ruta2,ruta3;

    private ObservableList<Destino> destinosObservables;

    private ArrayList<Destino> destinosActualizados;

    @FXML
    void agregarDestino(ActionEvent event) {

        try{           //SERVIDOR EN LOCALHOST EN ESTE CASO
            Socket socket = new Socket("localhost",9595);


            nombre = this.txtNombreDestino.getText();
            clima = this.txtClimaDestino.getText();
            ciudad = this.txtCiudadDestino.getText();
            descripcion = this.txtDescripcionDestino.getText();
            ruta1 = this.txtRutaImg1.getText();
            ruta2 = this.txtRutaImg2.getText();
            ruta3 = this.txtRutaImg3.getText();

            if(nombre.isBlank()||clima.isBlank()||ciudad.isBlank()||descripcion.isBlank()||ruta1.isBlank()||ruta2.isBlank()||ruta3.isBlank()){
                throw new CamposVaciosException();
            }

            ArrayList<byte[]> imagenes = new ArrayList<>();
            imagenes.add(Files.readAllBytes(Paths.get(ruta1)));
            imagenes.add(Files.readAllBytes(Paths.get(ruta2)));
            imagenes.add(Files.readAllBytes(Paths.get(ruta3)));

            Destino destinoCreado = new Destino(nombre,ciudad,imagenes,clima,descripcion);
            DataPaquete paqueteDatos = new DataPaquete("destino","crear",destinoCreado);


            ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
            flujoSalida.writeObject(paqueteDatos);
            flujoSalida.flush();


            ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
            Response response = (Response) flujoEntrada.readObject();
            ArrayList<Destino> destinosRecibidos = (ArrayList<Destino>) response.getObjetoRespuesta();

            flujoSalida.close();
            flujoEntrada.close();
            socket.close();

            if(response.getMensaje().equalsIgnoreCase("guardado")){
                new Alert(Alert.AlertType.CONFIRMATION,"Destino agregado con éxito").showAndWait();
                this.txtCiudadDestino.clear();
                this.txtClimaDestino.clear();
                this.txtDescripcionDestino.clear();
                this.txtNombreDestino.clear();
                this.txtRutaImg1.clear();
                this.txtRutaImg2.clear();
                this.txtRutaImg3.clear();
                updateList();
            }else{
                throw new RegistroExistenteException();
            }


        }catch (IOException e){
            e.printStackTrace();
        }catch (CamposVaciosException e){
            e.getAlert().showAndWait();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (RegistroExistenteException e){
            e.getAlert().showAndWait();
        }

    }

    @FXML
    void editarDestino(ActionEvent event) {

    }

    @FXML
    void eliminarDestino(ActionEvent event) {

    }

    @FXML
    void seleccionarDestino(MouseEvent event) {

        Destino destinoSeleccionado = this.tblDestino.getSelectionModel().getSelectedItem();

        if(destinoSeleccionado!=null){

            this.txtNombreDestino.setText(destinoSeleccionado.getNombre());
            this.txtClimaDestino.setText(destinoSeleccionado.getClima());
            this.txtCiudadDestino.setText(destinoSeleccionado.getCiudad());
            this.txtDescripcionDestino.setText(destinoSeleccionado.getDescripcion());

        }

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

    public void updateList(){

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
            this.colNombreDestino.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            this.colCiudadDestino.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
            this.colClimaDestino.setCellValueFactory(new PropertyValueFactory<>("clima"));

            this.destinosObservables.addAll(this.destinosActualizados);
            this.tblDestino.setItems(destinosObservables);




        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateList();

    }
}
