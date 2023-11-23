package co.edu.uniquindio.proyecto_viajes.admin.controller;

import co.edu.uniquindio.proyecto_viajes.DataPaquete;
import co.edu.uniquindio.proyecto_viajes.client.model.Destino;
import co.edu.uniquindio.proyecto_viajes.client.model.Guia;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GuiasController implements Initializable {

    @FXML
    private TableColumn<?, ?> colExperienciaGuia;

    @FXML
    private TableColumn<?, ?> colIdentificacionGuia;

    @FXML
    private TableColumn<?, ?> colNombreGuia;

    @FXML
    private TableView<Guia> tblGuias;

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

    private String nombre, identificacion, experiencia, idioma1, idioma2, idioma3;

    private ArrayList<Guia> guiasActualizados;

    private ObservableList<Guia> guiasObservables;


    @FXML
    void agregarGuia(ActionEvent event) {

        try{           //SERVIDOR EN LOCALHOST EN ESTE CASO
            Socket socket = new Socket("localhost",9595);


            nombre = this.txtNombreGuia.getText();
            identificacion = this.txtIdentificacionGuia.getText();
            experiencia = this.txtExperienciaGuia.getText();
            idioma1 = this.txtIdioma1Guia.getText();
            idioma2 = this.txtIdioma2Guia.getText();
            idioma3 = this.txtIdioma3Guia.getText();

            if(nombre.isBlank()||identificacion.isBlank()||experiencia.isBlank()||idioma1.isBlank()||idioma2.isBlank()||idioma3.isBlank()){
                new Alert(Alert.AlertType.ERROR,"Asegurese de llenar todos los campos", ButtonType.OK).showAndWait();
            }else{

                ArrayList<String> idiomasGuia = new ArrayList<>();
                idiomasGuia.add(idioma1);
                idiomasGuia.add(idioma2);
                idiomasGuia.add(idioma3);

                Guia guiaCreado = new Guia(nombre,identificacion,idiomasGuia,Integer.parseInt(experiencia));
                DataPaquete paqueteDatos = new DataPaquete("guia","crear",guiaCreado);


                ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                flujoSalida.writeObject(paqueteDatos);
                flujoSalida.flush();


                ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                Response response = (Response) flujoEntrada.readObject();

                flujoSalida.close();
                flujoEntrada.close();
                socket.close();

                if(response.getMensaje().equalsIgnoreCase("guardado")){
                    new Alert(Alert.AlertType.CONFIRMATION,"Destino agregado con éxito").showAndWait();
                    this.txtNombreGuia.clear();
                    this.txtExperienciaGuia.clear();
                    this.txtIdentificacionGuia.clear();
                    this.txtIdioma1Guia.clear();
                    this.txtIdioma2Guia.clear();
                    this.txtIdioma3Guia.clear();
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
    void editarGuia(ActionEvent event) {

        Guia guiaSeleccionado = this.tblGuias.getSelectionModel().getSelectedItem();

        if(guiaSeleccionado!=null){

            nombre = this.txtNombreGuia.getText();
            identificacion = this.txtIdentificacionGuia.getText();
            experiencia = this.txtExperienciaGuia.getText();
            idioma1 = this.txtIdioma1Guia.getText();
            idioma2 = this.txtIdioma2Guia.getText();
            idioma3 = this.txtIdioma3Guia.getText();


            if(nombre.isBlank()||identificacion.isBlank()||experiencia.isBlank()||idioma1.isBlank()||idioma2.isBlank()||idioma3.isBlank()){
                new Alert(Alert.AlertType.ERROR,"Asegurese de llenar todos los campos", ButtonType.OK).showAndWait();
            }else{

                try{

                    ArrayList<String> idiomasGuia = new ArrayList<>();
                    idiomasGuia.add(idioma1);
                    idiomasGuia.add(idioma2);
                    idiomasGuia.add(idioma3);


                    String identificacionGuiaSeleccionado = guiaSeleccionado.getIdentificacion();
                    Guia guiaNuevo = new Guia(nombre,identificacion,idiomasGuia,Integer.parseInt(experiencia));
                    ArrayList<Object> idViejoGuiaNuevo = new ArrayList<>();
                    idViejoGuiaNuevo.add(identificacionGuiaSeleccionado);
                    idViejoGuiaNuevo.add(guiaNuevo);

                    DataPaquete paquete = new DataPaquete("guia","editar",idViejoGuiaNuevo);

                    Socket socket = new Socket("localhost",9595);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(paquete);

                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Response response = (Response) objectInputStream.readObject();

                    objectInputStream.close();
                    objectOutputStream.close();
                    socket.close();

                    if(response.getMensaje().equalsIgnoreCase("editado")){
                        new Alert(Alert.AlertType.CONFIRMATION,"Guia editado con exito",ButtonType.OK).showAndWait();
                        this.txtNombreGuia.clear();
                        this.txtExperienciaGuia.clear();
                        this.txtIdentificacionGuia.clear();
                        this.txtIdioma1Guia.clear();
                        this.txtIdioma2Guia.clear();
                        this.txtIdioma3Guia.clear();
                        updateList();
                    }else{

                        new Alert(Alert.AlertType.ERROR,"No se pudo editar el guia",ButtonType.OK).showAndWait();

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }

    }

    @FXML
    void eliminarGuia(ActionEvent event) {

        Guia guiaSeleccionado = this.tblGuias.getSelectionModel().getSelectedItem();

        if(guiaSeleccionado!=null){
            try{

                DataPaquete paquete = new DataPaquete("guia","eliminar",guiaSeleccionado);
                Socket socket = new Socket("localhost",9595);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(paquete);

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Response response = (Response) objectInputStream.readObject();

                objectInputStream.close();
                objectOutputStream.close();
                socket.close();

                if(response.getMensaje().equalsIgnoreCase("eliminado")){

                    new Alert(Alert.AlertType.CONFIRMATION,"Guia eliminado con exito",ButtonType.OK).showAndWait();
                    this.txtNombreGuia.clear();
                    this.txtExperienciaGuia.clear();
                    this.txtIdentificacionGuia.clear();
                    this.txtIdioma1Guia.clear();
                    this.txtIdioma2Guia.clear();
                    this.txtIdioma3Guia.clear();
                    updateList();
                }else{

                    new Alert(Alert.AlertType.ERROR,"El guia que tratar de eliminar, no existe en la base de datos",ButtonType.OK).showAndWait();

                }



            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    @FXML
    void seleccionarGuia(MouseEvent event) {

        Guia guiaSeleccionado = this.tblGuias.getSelectionModel().getSelectedItem();

        if(guiaSeleccionado!=null){

            this.txtNombreGuia.setText(guiaSeleccionado.getNombreCompleto());
            this.txtIdentificacionGuia.setText(guiaSeleccionado.getIdentificacion());
            this.txtExperienciaGuia.setText(String.valueOf(guiaSeleccionado.getExperiencia()));
            this.txtIdioma1Guia.setText(guiaSeleccionado.getIdiomas().get(0));
            this.txtIdioma2Guia.setText(guiaSeleccionado.getIdiomas().get(1));
            this.txtIdioma3Guia.setText(guiaSeleccionado.getIdiomas().get(2));

        }

    }

    public void updateList(){
        try{

            Socket socket = new Socket("localhost",9595);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            DataPaquete dataPaquete = new DataPaquete("guia","listar",null);
            objectOutputStream.writeObject(dataPaquete);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            ArrayList<Guia> guiasRecibidos = (ArrayList<Guia>) objectInputStream.readObject();

            objectOutputStream.close();
            objectInputStream.close();
            socket.close();

            this.guiasActualizados = guiasRecibidos;

            guiasObservables = FXCollections.observableArrayList();
            this.colNombreGuia.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
            this.colIdentificacionGuia.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
            this.colExperienciaGuia.setCellValueFactory(new PropertyValueFactory<>("experiencia"));

            this.guiasObservables.addAll(this.guiasActualizados);
            this.tblGuias.setItems(guiasObservables);



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateList();

    }
}
