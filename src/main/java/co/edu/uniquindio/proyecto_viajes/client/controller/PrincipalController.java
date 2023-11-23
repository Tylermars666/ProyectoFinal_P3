package co.edu.uniquindio.proyecto_viajes.client.controller;

import co.edu.uniquindio.proyecto_viajes.DataPaquete;
import co.edu.uniquindio.proyecto_viajes.client.model.Destino;
import co.edu.uniquindio.proyecto_viajes.client.model.Paquete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML
    private Button btnAnterior;

    @FXML
    private Button btnSiguiente;

    @FXML
    private ComboBox<String> cmbFiltroBusqueda;

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
    private Label lblDescripcion;

    @FXML
    private TableView<Destino> tblCiudad;

    @FXML
    private TableView<Paquete> tblPaquetes;

    @FXML
    private TextArea txtADescripcionDestino;

    private ObservableList<Paquete> filtroObservablePaquete;

    private ObservableList<Paquete> paquetesObservables;

    private ObservableList<String>opcionesFiltro;

    private ArrayList<Paquete>paquetesActualizados;

    private ObservableList<Destino> destinosObservables;

    private int imgCont=1;

    @FXML
    private TextField txtBusqueda;

    private String valor_filtro;

    private String filtroSeleccionado;

    private Destino destinoSeleccionado;

    @FXML
    void abrirChat(ActionEvent event) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/client/view/chat-cliente-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Chat");
        stage.setScene(scene);

        ChatClienteController controller = loader.getController();
        stage.show();

    }

    @FXML
    void abrirEdicionPerfil(ActionEvent event) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/client/view/edicion-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Edicion");
        stage.setScene(scene);

        EdicionController controller = loader.getController();
        stage.show();

    }

    @FXML
    void abrirVentanaReserva(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_viajes/client/view/reserva-view.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Reserva");
        stage.setScene(scene);

        ReservaController controller = loader.getController();
        stage.show();

    }

    @FXML
    void buscar(ActionEvent event) {

    }

    @FXML
    void seleccionarCiudad(MouseEvent event) {
        destinoSeleccionado = this.tblCiudad.getSelectionModel().getSelectedItem();

        this.txtADescripcionDestino.setVisible(true);
        this.btnSiguiente.setVisible(true);
        this.btnAnterior.setVisible(true);
        this.txtADescripcionDestino.setText(destinoSeleccionado.getDescripcion());
        this.imgImagenCiudad.setImage(new Image(new ByteArrayInputStream(destinoSeleccionado.getImagenes().get(1))));
    }

    @FXML
    void seleccionarDestino(MouseEvent event) {
        Paquete paqueteSeleccionado = this.tblPaquetes.getSelectionModel().getSelectedItem();
        ArrayList<Destino> destinosDelPaquete = paqueteSeleccionado.getDestinos();

        destinosObservables = FXCollections.observableArrayList();
        this.colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        this.colClima.setCellValueFactory(new PropertyValueFactory<>("clima"));

        this.destinosObservables.addAll(destinosDelPaquete);
        this.tblCiudad.setItems(destinosObservables);
    }

    @FXML
    void seleccionarFiltro(ActionEvent event) {
        filtroSeleccionado = cmbFiltroBusqueda.getSelectionModel().getSelectedItem();
    }

    @FXML
    void verAnteriorImagen(ActionEvent event) {

        this.imgCont = imgCont-1;

        if(imgCont<0){
            imgCont = 2;
        }

        this.imgImagenCiudad.setImage(new Image(new ByteArrayInputStream(destinoSeleccionado.getImagenes().get(imgCont))));

    }

    @FXML
    void verSiguienteImagen(ActionEvent event) {

        this.imgCont = imgCont+1;

        if(imgCont>2){
            imgCont =0;
        }

        this.imgImagenCiudad.setImage(new Image(new ByteArrayInputStream(destinoSeleccionado.getImagenes().get(imgCont))));

    }

    @FXML
    void filtrarTexto(KeyEvent event) {

        switch (filtroSeleccionado){

            case "Precio":
                filtrarPorPrecio();
                break;

            case "Duracion":
                filtrarPorDuracion();
                break;
            case "Cupo":
                filtrarPorCupo();
                break;

        }

    }

    public void filtrarPorPrecio() {

        valor_filtro = this.txtBusqueda.getText();
        if (valor_filtro.isEmpty()) {
            this.tblPaquetes.setItems(paquetesObservables);
        } else {
            this.filtroObservablePaquete.clear();
            for (Paquete paquete : this.paquetesObservables) {
                if (String.valueOf(paquete.getPrecio()).equalsIgnoreCase(valor_filtro)) {
                    this.filtroObservablePaquete.add(paquete);
                }
            }
            this.tblPaquetes.setItems(filtroObservablePaquete);
        }

    }

    public void filtrarPorDuracion(){

        valor_filtro = this.txtBusqueda.getText();
        if (valor_filtro.isEmpty()) {
            this.tblPaquetes.setItems(paquetesObservables);
        } else {
            this.filtroObservablePaquete.clear();
            for (Paquete paquete : this.paquetesObservables) {
                if (paquete.getDuracion()==Integer.parseInt(valor_filtro)) {
                    this.filtroObservablePaquete.add(paquete);
                }
            }
            this.tblPaquetes.setItems(filtroObservablePaquete);
        }

    }

    public void filtrarPorCupo(){

        valor_filtro = this.txtBusqueda.getText();
        if (valor_filtro.isEmpty()) {
            this.tblPaquetes.setItems(paquetesObservables);
        } else {
            this.filtroObservablePaquete.clear();
            for (Paquete paquete : this.paquetesObservables) {
                if (paquete.getCupoMax()==Integer.parseInt(valor_filtro)) {
                    this.filtroObservablePaquete.add(paquete);
                }
            }
            this.tblPaquetes.setItems(filtroObservablePaquete);
        }

    }

    public void updateList(){

        try{

            Socket socket = new Socket("localhost",9595);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            DataPaquete dataPaquete = new DataPaquete("paquete","listar",null);
            objectOutputStream.writeObject(dataPaquete);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            ArrayList<Paquete> paquetesRecibido = (ArrayList<Paquete>) objectInputStream.readObject();

            objectOutputStream.close();
            objectInputStream.close();
            socket.close();

            this.paquetesActualizados = paquetesRecibido;

            paquetesObservables = FXCollections.observableArrayList();
            this.colNombrePaquete.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            this.colDuracionPaquete.setCellValueFactory(new PropertyValueFactory<>("duracion"));
            this.colPrecioPaquete.setCellValueFactory(new PropertyValueFactory<>("precio"));
            this.colFechaLimitePaquete.setCellValueFactory(new PropertyValueFactory<>("fechaLimite"));
            this.colCupoPaquete.setCellValueFactory(new PropertyValueFactory<>("cupoMax"));

            this.paquetesObservables.addAll(this.paquetesActualizados);
            this.tblPaquetes.setItems(paquetesObservables);






        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        opcionesFiltro = FXCollections.observableArrayList("Precio","Duracion","Cupo");
        cmbFiltroBusqueda.setItems(opcionesFiltro);
        filtroObservablePaquete = FXCollections.observableArrayList();


        this.txtADescripcionDestino.setVisible(false);
        this.btnAnterior.setVisible(false);
        this.btnSiguiente.setVisible(false);
        this.lblDescripcion.setVisible(false);



        updateList();
    }
}
